package repositories


import scala.util.{Failure, Success}
import models.User
import org.joda.time.DateTime


import anorm.SqlParser._
import anorm._
import play.api.db.DB
import scala.language.postfixOps
import play.api.Play.current
import java.sql.Timestamp
/**
  * Created by corpus on 05/02/2016.
  */
trait UserRepository {
  private[repositories] val recordMapper = {
    long("users.id") ~
      str("users.email") ~
      bool("users.email_confirmed") ~
      get[Option[String]]("users.first_name") ~
      get[Option[String]]("users.last_name") ~
      str("users.password") ~
      str("users.services") ~
      get[DateTime]("users.registration_date") map {
      case id ~ email ~ emailConfirmed ~ firstName ~ lastName ~ password ~ services ~ registrationDate => {
        User(Some(id), email, emailConfirmed, firstName.getOrElse[String](""), lastName.getOrElse[String](""),
          password, services, registrationDate)
      }
    }
  }

}

object UserRepository extends UserRepository {


  def getByEmail(email: String): Option[User] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT users.*
          FROM users
          WHERE users.email = {email}
        """
      )
        .on("email" -> email)
        .as(recordMapper.singleOpt)
    }
  }

  def create(user: User): Option[User] = {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into users (email, email_confirmed,first_name,last_name,password,url_photo_profile,services,registration_date) values
        ({email},{email_confirmed},{first_name},{last_name},{password},{url_photo_profile},{services},{registration_date})
        """
      ).on(
        'email -> user.email,
        'email_confirmed -> user.emailConfirmed,
        'first_name -> user.firstName,
        'last_name -> user.lastName,
        'password -> user.password,
        'url_photo_profile-> "img/profile_default_large",
        'services -> user.services,
        'registration_date -> new Timestamp(user.registrationDate.getMillis())
      ).executeInsert()
    }
    getByEmail(user.email)
  }

  def update(user: User): Option[User] = {
    println("bug")
    println(user.password)
    DB.withConnection { implicit c =>
      SQL(
        """
        update  users set first_name ={first_name},last_name={last_name},email = {email},
        email_confirmed={email_confirmed}, password={password}, services={services} where id ={id}
        """
      ).on(
        'id -> user.id.get,
        'email -> user.email,
        'email_confirmed -> user.emailConfirmed,
        'first_name -> user.firstName,
        'last_name -> user.lastName,
        'email -> user.email,
        'email_confirmed -> user.emailConfirmed,
        'password -> user.password,
        'services -> user.services
      ).executeUpdate()
    }
    getByEmail(user.email)
  }

  def save(user: User): Option[User] = {

    user.id match {
      case None => create(user)
      case Some(id) => update(user)
    }
  }

  def remove(email: String) = {

    DB.withConnection { implicit c =>
      SQL(
        """
          delete from users  where email= {email}
        """
      ).on(
        'email -> email
      ).executeUpdate()
    }
  }

  def getAuthorByArticle(articleId: Long): Option[User] = {
    DB.withConnection { implicit c =>
      SQL(
        """
        SELECT users.*
        FROM users
        INNER JOIN articles ON articles.author_id = users.id
        WHERE articles.id = {articleId}
        """
      ).on(
        "articleId" -> articleId
      ).as(recordMapper.singleOpt)
    }

  }
}

package repositories

import reactivemongo.api.collections.bson
import repositories._
import scala.util.{Failure, Success}
import models.User
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
import reactivemongo.bson._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
      list[String]("users.services") ~
      get[DateTime]("users.registration_date") map {
      case id ~ email ~ emailConfirmed ~ firstName ~ lastName ~ password ~ services ~ registrationDate => {
        User(Some(id), email, emailConfirmed, firstName.getOrElse[String](""), lastName.getOrElse[String](""),
          password, services, registrationDate)
      }
    }
  }

}

object UserRepository extends UserRepository {
  //
  //
  //  implicit object userReader extends BSONDocumentReader[User] {
  //    def read(doc: BSONDocument): User = {
  //
  //      val optionId = doc.getAs[BSONObjectID]("_id") match {
  //        case None => None
  //        case Some(id) => Some(id.toString().substring(14, 38))
  //      }
  //      //check the implicit conversion
  //      User(optionId,
  //        doc.getAs[String]("email").get,
  //        doc.getAs[Boolean]("emailConfirmed").get,
  //        doc.getAs[String]("firstName").getOrElse("error.noFirstName"),
  //        doc.getAs[String]("lastName").getOrElse("error.noLastName"),
  //        doc.getAs[String]("password").get,
  //        doc.getAs[List[String]]("services").get
  //      )
  //    }
  //  }
  //
  //  implicit object userWriter extends BSONDocumentWriter[User] {
  //
  //    def write(user: User): BSONDocument = {
  //      val doc = BSONDocument(
  //        "email" -> user.email,
  //        "emailConfirmed" -> user.emailConfirmed,
  //        "firstName" -> user.firstName,
  //        "lastName" -> user.lastName,
  //        "password" -> user.password,
  //        "services" -> user.services
  //      )
  //      user.id match {
  //        case None => doc
  //        case Some(id) => doc.add(BSONDocument("_id" -> BSONObjectID(id)))
  //      }
  //    }
  //  }


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
        insert into users (email, email_confirmed,first_name,last_name,password,services,registration_date) values
        ({email},{email_confirmed},{first_name},{last_name},{password},{services},{registration_date})
        """
      ).on(
        'email -> user.email,
        'email_confirmed -> user.emailConfirmed,
        'first_name -> user.firstName,
        'last_name -> user.lastName,
        'password -> user.password,
        'services -> user.services,
        'registration_date -> new Timestamp(user.registrationDate.getMillis())
      ).executeInsert()
    }
    getByEmail(user.email)
  }

  def update(user: User): Option[User] = {
    DB.withConnection { implicit c =>
      SQL(
        """ 
        update  users set first_name ={first_name},last_name={last_name},password={password},
        services={services} where id ={id}
        """
      ).on(
        'id -> user.id.get,
        'first_name -> user.firstName,
        'last_name -> user.lastName,
        'encrypted_password -> user.password,
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
}

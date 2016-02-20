package repositories

/**
  * Created by corpus on 19/02/2016.
  */


import scala.util.{Failure, Success}
import models.{Article,User}
import org.joda.time.DateTime


import anorm.SqlParser._
import anorm._
import anorm.Sql._
import play.api.db.DB
import scala.language.postfixOps
import play.api.Play.current
import java.sql.Timestamp

/**
  * Created by corpus on 05/02/2016.
  */
trait ArticleRepository {
  private[repositories] val recordMapper = {
    long("articles.id") ~
      get[DateTime]("articles.creation_date") ~
      get[DateTime]("articles.last_update") ~
      str("articles.title") ~
      str("articles.content") ~
      int("articles.nb_modifications") ~
      int("articles.reading_time") ~
      str("articles.tag1") ~
      str("articles.tag2") map {
      case id ~ creationDate ~ lastUpdate ~ title ~ content ~ nbModifications ~
        readingTime ~ tag1 ~ tag2 => {
        Article(Some(id), creationDate, lastUpdate,
          title, content, nbModifications, readingTime, tag1, Some(tag2))
      }
    }
  }

}

object ArticleRepository extends ArticleRepository {

  def getById(articleId: Long): Option[Article] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.id = {id}
        """
      )
        .on("id" -> articleId)
        .as(recordMapper.singleOpt)
    }
  }

  def getByAuthotAndTitle(authorId: Long, title: String): Option[Article] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.author_id = {authorId} AND articles.title = {title}
        """
      )
        .on(
          "authorId" -> authorId,
          "title" -> title
        )
        .as(recordMapper.singleOpt)
    }

  }

  def create(authorId: Long, article: Article): Option[Article]= {

    DB.withConnection { implicit c =>
      SQL(
        """
        insert into articles (author_id,creation_date,last_update,title,content,nb_modifications,reading_time, tag1, tag2) values
        ({author_id},{creation_date},{last_update},{title},{content},{nb_modifications},{reading_time}, {tag1},{tag2})
        """
      ).on(
        'author_id -> authorId,
        'creation_date -> new Timestamp(article.creationDate.getMillis()),
        'last_update -> new Timestamp(article.lastUpdate.getMillis()),
        'title -> article.title,
        'content -> article.content,
        'nb_modifications -> article.nbModifications,
        'reading_time -> article.content.length/1150,
        'tag1 -> article.tag1,
        'tag2 -> article.tag2.getOrElse[String]("")
      ).executeInsert()
    }
    getByAuthotAndTitle(authorId,article.title)
  }

  def update(article: Article) = {

    DB.withConnection { implicit c =>
      SQL(
        """ 
        update  articles set last_update ={last_update},title={title},content={content},
        nbModifications={nbModifications} readingTime={readingTime} where id ={id}
        """
      ).on(
        'id -> article.id.get,
        'last_update -> new Timestamp(DateTime.now().getMillis()),
        'title -> article.title,
        'content -> article.content,
        'nbModifications -> (article.nbModifications + 1),
        'readingTime -> article.content.length / 1150
      ).executeUpdate()
    }
    getById(article.id.get)
  }

  def save(author: User, article: Article) = {
    article.id match {
      case None => create(author.id.get,article)
      case Some(id) => update(article)
    }
  }


  def getAllArticles(): List[Article] = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusHours(24).getMillis())

    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT * FROM articles
          WHERE articles.creation_date > {nowMinus1Day}
        """
      )
        .on("nowMinus1Day" -> datetime)
        .as(recordMapper *)
        .toList
    }

  }
}
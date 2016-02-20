package repositories

/**
  * Created by corpus on 19/02/2016.
  */


import scala.util.{Failure, Success}
import models.Article
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
trait ArticleRepository {
  private[repositories] val recordMapper = {
    long("articles.id") ~
      long("articles.author_id") ~
      get[DateTime]("articles.creation_date") ~
      get[DateTime]("articles.last_update") ~
      str("articles.title") ~
      str("articles.content") ~
      int("articles.nb_modifications") ~
      int("articles.reading_time") map {
      case id ~ author_id ~ creationDate ~ lastUpdate ~ title ~ content ~ nbModifications ~
        readingTime => {
        Article(Some(id), author_id, creationDate, lastUpdate,
          title, content, nbModifications, readingTime)
      }
    }
  }

}

object ArticleRepository extends ArticleRepository {

  def getById(articleId: Long) = {}

  def create(article: Article) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into articles (author_id,creation_date,last_update,title,content,_nb_modifications,reading_time) values
        ({author_id},{creation_date},{last_update},{title},{content},{nb_modifications},{reading_time})
        """
      ).on(
        'author_id -> article.author_id,
        'creation_date -> new Timestamp(article.creationDate.getMillis()),
        'last_update -> new Timestamp(article.lastUpdate.getMillis()),
        'title -> article.title,
        'content -> article.content,
        'nb_modifications -> article.nbModifications,
        'readingTime -> article.readingTime
      ).executeInsert()
    }
  }
}
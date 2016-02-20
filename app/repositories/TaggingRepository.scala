package repositories

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current

import repositories.TagRepository

/**
  * Created by corpus on 20/02/2016.
  */
trait TaggingRepository {
  private[repositories] val recordMapperName = {
    str("tags.name") map {
      case name => {
        name
      }
    }
  }
}

object TaggingRepository extends TaggingRepository {
  def create(tagName: String, articleId: Long) = {
    TagRepository.create((tagName))

    DB.withConnection { implicit c =>
      SQL(
        """
        insert into taggings (tag_name,article_id,tagging_date) values
        ({tag_name},{article_id},{tagging_date})
        """
      ).on(
        'tag_name -> tagName.toLowerCase(),
        'article_id -> articleId,
        'tagging_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
  }

  def remove(tagName: String, articleId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM taggings
        WHERE taggings.tag_name = {name} AND taggings.article_id = {articleId}
        """).
        on(
          "name" ->tagName.toLowerCase(),
          "articleId" -> articleId
        ).executeUpdate()
      "tag.remove.success"
    }
  }

  def exists(tagName: String, articleId: Long): Boolean = {
    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT tags.name from tags
      WHERE tags.name = {name} and tags.article_id = {article_id}
        """
      ).
        on("name" ->tagName,
          "article_id" -> articleId
        )
        .as(recordMapperName.singleOpt) match {
        case None => false
        case Some(name) => true
      }

    }
  }

  def removeFromArticle(articleId: Long) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM taggings
        WHERE taggings.article_id = {articleId}
        """).
        on(
          "articleId" -> articleId
        ).executeUpdate()
      "tags.remove.success"
    }

  }
}
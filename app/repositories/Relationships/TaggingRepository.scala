package repositories.Relationships

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
import repositories.Contents.{ArticleRepository, TagRepository}

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

  //Creating taggings only if the article is not a draft.
  def create(tagName: String,authorId: Long, articleId: Long) = {
    ArticleRepository.getNoDraftById(articleId) match {
      case Some(article) => {
        TagRepository.create((tagName))
        DB.withConnection { implicit c =>
          SQL(
            """
        insert into taggings (tag_name,author_id,article_id,tagging_date) values
        ({tag_name},{authorId},{article_id},{tagging_date})
            """
          ).on(
            'tag_name -> tagName.toLowerCase(),
            'authorId -> authorId,
            'article_id -> articleId,
            'tagging_date -> new Timestamp(DateTime.now().getMillis())
          ).executeInsert()
        }
        "tagging.add.success"
      }
      case None => "article.notFound"
    }
  }

  def remove(tagName: String, articleId: Long) = {
    ArticleRepository.getById(articleId) match {
      case Some(article) => {
        DB.withConnection { implicit c =>
          SQL(
            """
        DELETE FROM taggings
        WHERE taggings.tag_name = {name} AND taggings.article_id = {articleId}
            """).
            on(
              "name" -> tagName.toLowerCase(),
              "articleId" -> articleId
            ).executeUpdate()
        }
        "tagging.remove.success"

      }
      case None => "article.notFound"
    }
  }

  def exists(tagName: String, articleId: Long): Boolean = {
    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT taggings.tag_name from taggings
      WHERE taggings.tag_name = {name} and taggings.article_id = {article_id}
        """
      ).
        on("name" -> tagName,
          "article_id" -> articleId
        )
        .as(recordMapperName.singleOpt) match {
        case None => false
        case Some(name) => true
      }

    }
  }

  //No matter if it is a draft
  def tagsFromArticle(articleId: Long): List[String] = {
    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT taggings.tag_name from taggings
      WHERE taggings.article_id = {article_id}
        """
      ).
        on(
          "article_id" -> articleId
        )
        .as(recordMapperName *)
        .toList
    }

  }

  def howManyTagsForArticle(articleId: Long): Int = {
    tagsFromArticle(articleId).length
  }


  def removeFromArticle(articleId: Long) = {
    if (ArticleRepository.isDraft(articleId)) "error.isDraft"
    else {
      DB.withConnection { implicit c =>
        SQL(
          """
        DELETE FROM taggings
        WHERE taggings.article_id = {articleId}
          """).
          on(
            "articleId" -> articleId
          ).executeUpdate()
        "taggings.remove.success"
      }
    }
  }
}
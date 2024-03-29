package repositories.Relationships

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
import repositories.Contents.ArticleRepository


/**
  * Created by corpus on 20/02/2016.
  */
trait LikeRepository {
  private[repositories] val recordMapperId = {
    long("likes.id") map {
      case id => {
        id
      }
    }
  }
}

object LikeRepository extends LikeRepository {

  def create(userId: Long, authorId: Long, articleId: Long) = {

    ArticleRepository.getById(articleId) match {
      case Some(article) => {
        DB.withConnection { implicit c =>
          SQL(
            """
        insert into likes (user_id,author_id,article_id,like_date) values
        ({userId},{authorId},{articleId},{likeDate})
            """
          ).on(
            'userId -> userId,
            'authorId -> authorId,
            'articleId -> articleId,
            'likeDate -> new Timestamp(DateTime.now().getMillis())
          ).executeInsert()
        }
        "like.add.success"
      }
      case None => "article.notFound"
    }

  }

  def remove(userId: Long, articleId: Long) = {

    ArticleRepository.getById(articleId) match {
      case Some(article) => {
        DB.withConnection { implicit c =>
          SQL(
            """
        DELETE FROM likes
        WHERE likes.user_id = {userId} and likes.article_id = {articleId}
            """).
            on(
              "userId" -> userId,
              "articleId" -> articleId
            ).
            executeUpdate()

        }
        "like.remove.success"
      }
      case None => "article.notFound"
    }
  }

  def hasLiked(userId: Long, articleId: Long): Boolean = {

    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT likes.id from likes
      WHERE likes.user_id = {userId} and likes.article_id = {articleId}
        """
      ).
        on("userId" -> userId,
          "articleId" -> articleId
        )
        .as(recordMapperId.singleOpt) match {
        case None => false
        case Some(id) => true
      }


    }

  }

  def likesOrUnlikes(userId: Long, authorId: Long, articleId: Long): String = {
    if (ArticleRepository.isDraft(articleId)) "error.isDraft"
    else {
      if (hasLiked(userId, articleId)) remove(userId, articleId)
      else create(userId, authorId, articleId)
    }
  }
}
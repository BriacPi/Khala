package repositories

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current

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

  def create(userId: Long, articleId: Long) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into likes (user_id,article_id,like_date) values
        ({user_id},{article_id},{like_date})
        """
      ).on(
        'user_id -> userId,
        'article_id -> articleId,
        'like_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
  }

  def remove(userId: Long, articleId: Long) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM likes
        WHERE likes.user_id = {userId} and likes.article_id = {article_id}
        """).
        on(
          "userId" -> userId,
          "article_id" -> articleId
        ).executeUpdate()
    }
  }

  def hasLiked(userId: Long, articleId: Long): Boolean = {

    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT likes.id from likes
      WHERE likes.user_id = {userId} and likes.article_id = {article_id}
        """
      ).
        on("userId" -> userId,
          "article_id" -> articleId
        )
        .as(recordMapperId.singleOpt) match {
        case None => false
        case Some(id) => true
      }


    }

  }

  def likesOrUnlikes(userId: Long, articleId: Long): String =
    if (hasLiked(userId, articleId)) {
      remove(userId, articleId)
      "like.remove.success"
    }
    else {
      create(userId, articleId)
      "like.add.success"
    }


}
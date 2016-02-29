package repositories.Relationships

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
/**
  * Created by corpus on 21/02/2016.
  */
trait FollowRepository {
  private[repositories] val recordMapperId = {
    long("follows.id") map {
      case id => {
        id
      }
    }
  }


}


object FollowRepository extends FollowRepository {


  def follows(followerId: Long, authorId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        insert into follows (follower_id,author_id,follow_date) values
        ({followerId},{authorId},{followDate})
        """
      ).on(
        'followerId -> followerId,
        'authorId -> authorId,
        'followDate -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
    "follow.success"
  }

  def unfollows(followerId: Long, authorId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM follows
        WHERE follows.follower_id = {followerId} AND follows.author_id = {authorId}
        """).
        on(
          "followerId" -> followerId,
          "authorId" -> authorId
        ).executeUpdate()
    }
    "unfollow.success"
  }

  def hasFollowed(followerId: Long, authorId: Long): Boolean = {

    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT follows.id from follows
      WHERE follows.follower_id = {followerId} and follows.author_id = {authorId}
        """
      ).
        on("followerId" -> followerId,
          "authorId" -> authorId
        )
        .as(recordMapperId.singleOpt) match {
        case None => false
        case Some(id) => true
      }
    }
  }

  def followsOrUnfollows(followerId: Long, authorId: Long): String = {
    if (hasFollowed(followerId, authorId)) {
      unfollows(followerId, authorId)
    }
    else {
      follows(followerId, authorId)

    }

  }

}

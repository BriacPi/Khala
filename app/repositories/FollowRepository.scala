package repositories

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current
/**
  * Created by corpus on 21/02/2016.
  */
trait FollowRepository {

}


object FollowRepository extends FollowRepository {
  private[repositories] val recordMapperId = {
    long("follows.id") map {
      case id => {
        id
      }
    }
  }


  def follows(followerId: Long, authorId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        insert into follows (follower_id,author_id,follow_date) values
        ({follower_id},{author_id},{follow_date})
        """
      ).on(
        'follower_id -> followerId,
        'author_id -> authorId,
        'follow_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
  }

  def unfollows(followerId: Long, authorId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM follows
        WHERE follows.follower_id = {follower_id} AND follows.author_id = {author_id}
        """).
        on(
          "follower_id" -> followerId,
          "author_id" -> authorId
        ).executeUpdate()
    }
  }

  def hasFollowed(followerId: Long, authorId: Long): Boolean = {

    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT follows.id from follows
      WHERE follows.follower_id = {followerId} and follows.author_id = {author_id}
        """
      ).
        on("followerId" -> followerId,
          "author_id" -> authorId
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
      "follow.success"
    }
    else {
      follows(followerId, authorId)
      "unfollow.success"
    }

  }

}

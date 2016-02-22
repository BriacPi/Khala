package repositories

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import models.AuthorNbs
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current
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
        ({follower_id},{author_id},{follow_date})
        """
      ).on(
        'follower_id -> followerId,
        'author_id -> authorId,
        'follow_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
    UserRepository.updateAuthorStats(authorId,AuthorNbs(authorId,1,0))
    "follow.success"
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
    UserRepository.updateAuthorStats(authorId,AuthorNbs(authorId,-1,0))
    "unfollow.success"
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
    }
    else {
      follows(followerId, authorId)

    }

  }

}

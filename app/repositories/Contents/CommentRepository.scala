package repositories.Contents

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import models._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB

import scala.language.postfixOps

/**
  * Created by corpus on 24/02/2016.
  */
trait CommentRepository {
  private[repositories] val recordMapperComment = {
    long("comments.id") ~
      long("comments.user_id") ~
      long("comments.parent_id") ~
      str("comments.parent_type") ~
      get[DateTime]("comments.creation_date") ~
      get[DateTime]("comments.last_update") ~
      str("comments.content") map {
      case id ~ user_id ~ parent_id ~ parent_type ~ creationDate ~ lastUpdate ~ content => {
        Comment(Some(id), user_id, parent_id, parent_type, creationDate, lastUpdate, content)
      }
    }
  }

  private[repositories] val recordMapperId = {
    long("comments.id") map {
      case id => {
        id
      }
    }
  }
}


object CommentRepository extends CommentRepository {

  def getByUserAndDate(userId: Long, creationDate: DateTime): Option[Long] = {
    DB.withConnection { implicit c =>
      SQL(
        """
          SELECT id FROM comments
          WHERE user_id = {userId} AND creation_date = {creationDate}
        """
      )
        .on(
        "userId" -> userId,
        "creationDate" -> new Timestamp(creationDate.getMillis())
      ).as(recordMapperId.singleOpt)

    }
  }

  def create(comment: Comment)= {
    if (!comment.id.isEmpty) "Authorization: denied."
    else {

      DB.withConnection { implicit c =>
        SQL(
          """
              INSERT into comments(user_id,parent_id,parent_type,creation_date,last_update,content)
              VALUES({userId},{parentId},{parentType},{creationDate},{lastUpdate},{content})
          """
        ).on(
          "userId" -> comment.userId,
          "parentId" -> comment.parentId,
          "parentType" -> comment.parentType,
          "creationDate" -> new Timestamp(comment.creationDate.getMillis()),
          "lastUpdate" -> new Timestamp(comment.lastUpdate.getMillis()),
          "content" -> comment.content
        ).executeInsert()
      }
    }
    //blame it on the boogie.
  }

  def update(userId: Long, comment: Comment) = {

    if (userId != comment.userId || comment.id.isEmpty) "Forget it, why are you so stubborn?"
    else {
      DB.withConnection { implicit c =>
        SQL(
          """
              UPDATE comments SET creation_date = {creationDate}, lastUpdate = {lastUpdate}, content = {content}
          """
        ).on(
          "creationDate" ->  new Timestamp(comment.creationDate.getMillis()),
          "lastUpdate" -> new Timestamp(DateTime.now().getMillis()),
          "content" -> comment.content
        ).executeUpdate()
      }
    }
    "comment.update.success"
  }

  def save(userId: Long, comment: Comment) = {
    comment.id match {
      case None => create(comment)
      case Some(id) => update(userId, comment)
    }
  }

  def delete(userId: Long, comment: Comment) = {
    if (comment.userId != userId) "Oh, please have mercy with other people's work."
    else {
      DB.withConnection { implicit c =>
        SQL(
          """
              DELETE FROM comments
              WHERE id = {commentId}
          """
        ).on(
          "commentId" -> comment.id.get
        ).executeUpdate()
      }
    }
    "We're so sad you deleted this jewel, but it's only to come back stronger, we know it!"
  }
}
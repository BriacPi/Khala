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
      case id ~ user_id ~ parent_id ~ parent_type ~ creationDate ~ lastUpdate ~ content   => {
        Comment(Some(id), user_id, parent_id, parent_type, creationDate, lastUpdate, content)
      }
    }
  }
}


object CommentRepository extends CommentRepository {


}
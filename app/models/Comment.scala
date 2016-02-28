package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import org.jsoup.Jsoup

/**
  * Created by corpus on 24/02/2016.
  */
case class Comment(
                    id: Option[Long],
                    userId: Long,
                    parentId: Long,
                    parentType: String = "article",
                    creationDate: DateTime = DateTime.now(),
                    lastUpdate: DateTime = DateTime.now(),
                    content: String
                  )
{
  def this(ce: CommentEditable,creationDate: DateTime,lastUpdate: DateTime) = this(ce.id,ce.userId,ce.parentId,
 ce.parentType,creationDate,lastUpdate,ce.content)
}
object Comment {
  implicit val commentReader: Reads[Comment] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "user_id").read[Long] and
      (JsPath \ "parent_id").read[Long] and
      (JsPath \ "parentType").read[String] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "content").read[String]
    ) (Comment.apply _)

  implicit val commentWriter = new Writes[Comment] {
    def writes(comment: Comment): JsObject = {
      def json = Json.obj(
        "user_id" -> comment.userId,
        "parentId" -> comment.parentId,
        "parentType" -> comment.parentType,
        "content" -> comment.content,
        "creationDate" -> comment.creationDate,
        "lastUpdate" -> comment.lastUpdate,
        "content" -> comment.content
      )
      comment.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)
      }
    }
  }
}

case class CommentEditable(
                            id: Option[Long],
                            userId: Long,
                            parentId: Long,
                            parentType: String,
                            content: String
                          )


object CommentEditable {
  implicit val commentEditableReader: Reads[CommentEditable] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "user_id").read[Long] and
      (JsPath \ "parent_id").read[Long] and
      (JsPath \ "parentType").read[String] and
      (JsPath \ "content").read[String]
    ) (CommentEditable.apply _)

  implicit val commentEditableWriter = new Writes[CommentEditable] {
    def writes(commentEditable: CommentEditable): JsObject = {
      def json = Json.obj(
        "user_id" -> commentEditable.userId,
        "parentId" -> commentEditable.parentId,
        "parentType" -> commentEditable.parentType,
        "content" -> commentEditable.content
      )
      commentEditable.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)
      }
    }
  }
}
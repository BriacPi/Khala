package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by corpus on 02/02/2016.
  */

//the relationship not the names.
case class Tagging(
                    id: Option[String],
                    tag_name: String,
                    author_id: String,
                    article_id: String,
                    creationDate: DateTime
                  )

object Tagging {


  implicit val taggingReader: Reads[Tagging] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "tag_name").read[String] and
      (JsPath \ "author_id").read[String] and
      (JsPath \ "article_id").read[String] and
      (JsPath \ "creationDate").read[DateTime]
    ) (Tagging.apply _)

  implicit val taggingWriter = new Writes[Tagging] {
    def writes(tagging: Tagging): JsObject = {
      def json = Json.obj(
        "tag_name" -> tagging.tag_name,
        "author_id" -> tagging.author_id,
        "article_id" -> tagging.article_id,
        "creationDate" -> tagging.creationDate
      )
      tagging.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }
  }
}

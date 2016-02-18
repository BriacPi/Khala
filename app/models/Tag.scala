package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by corpus on 18/02/2016.
  */
case class Tag(
                //id: String,
                name: String,
                nbArticles: Int,
                nbFollowers: Int,
                urlImage: String,
                creationDate: DateTime

              )


object Tag {

  implicit val tagReader: Reads[Tag] = (
    //readNullable manages option

    (JsPath \ "name").read[String] and
      (JsPath \ "nbArticles").read[Int] and
      (JsPath \ "nbFollowers").read[Int] and
      (JsPath \ "urlImage").read[String] and
      (JsPath \ "creationDate").read[DateTime]
    ) (Tag.apply _)

  implicit val tagWriter = new Writes[Tag] {
    def writes(tag: Tag): JsObject = {
      Json.obj(
        "name" -> tag.name,
        "nbArticles" -> tag.nbArticles,
        "nbFollowers" -> tag.nbFollowers,
        "urlImage" -> tag.urlImage,
        "creationDate" -> tag.creationDate
      )

    }
  }
}
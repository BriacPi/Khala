package models

import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by corpus on 17/02/2016.
  */
case class View(
               id: Option[String],
               user_id: String,
               article_id: String,
               creationDate: DateTime

                    )


object View {


  implicit val userReader: Reads[View] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "user_id").read[String] and
      (JsPath \ "article_id").read[String] and
      (JsPath \ "creationDate").read[DateTime]
    ) (View.apply _)

  implicit val userWriter = new Writes[View] {
    def writes(view: View): JsObject = {
      def json = Json.obj(
        "user_id" -> view.user_id,
        "article_id" -> view.article_id,
        "creationDate" -> view.creationDate
      )
      view.id match {
        case None => json
        case Some(id) =>(Json.obj("_id" -> id)).++(json)

      }
    }
  }
}

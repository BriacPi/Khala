package models

import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import reactivemongo.bson.BSONDocument

/**
  * Created by corpus on 18/02/2016.
  */
case class Follow (
                    id: Option[String],
                    follower_id: String,
                    author_id: String,
                    creationDate: DateTime
                  )


object Follow {


  implicit val followReader: Reads[Follow] = (
    //readNullable manages option

    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "follower_id").read[String] and
      (JsPath \ "author_id").read[String] and
      (JsPath \ "creationDate").read[DateTime]
    ) (Follow.apply _)

  implicit val followWriter = new Writes[Follow] {
    def writes(follow: Follow): JsObject = {
      Json.obj(
        "_id" -> follow.id,
        "follower_id" -> follow.follower_id,
        "author_id" -> follow.author_id,
        "creationDate" -> follow.creationDate
      )

    }
  }

}

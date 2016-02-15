package models.userProfile

import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
  * Created by corpus on 14/02/2016.
  */
case class Influence (
                           totalNbLikes: Int,
                           nbFollowers: Int,
                           nbFollowings: Int,
                           nbArticles: Int

                         )



object Influence {


  implicit val influenceReader: Reads[Influence] = (
    (JsPath \ "totalNbLikes").read[Int] and
      (JsPath \ "nbFollowers").read[Int] and
      (JsPath \ "nbFollowings").read[Int] and
      (JsPath \ "nbArticles").read[Int]

    ) (Influence.apply _)

  implicit val influenceWriter = new Writes[Influence] {
    def writes(influence: Influence): JsObject = Json.obj(
      "totalNbLikes" -> influence.totalNbLikes,
      "nbFollowers" -> influence.nbFollowers,
      "nbFollowings" -> influence.nbFollowings,
      "nbArticles" -> influence.nbArticles
    )

  }

}
package models.userProfile

import models.Article
import play.api.libs.json._
import play.api.libs.functional.syntax._


/**
  * Created by corpus on 14/02/2016.
  */
case class Influence(
                      totalNbLikes: Int,
                      nbFollowers: Int,
                      nbFollowings: Int,
                      nbArticles: Int,
                      //Concretely Object is a duet (Article,Int) corresponding to an article and its number of likes.
                      listArticles: List[Article]
                    )


object Influence {


  implicit val influenceReader: Reads[Influence] = (
    (JsPath \ "totalNbLikes").read[Int] and
      (JsPath \ "nbFollowers").read[Int] and
      (JsPath \ "nbFollowings").read[Int] and
      (JsPath \ "nbArticles").read[Int] and
      //we have a list of Json and we want to read it as list of article
      //      (JsPath \ "listArticles").read[Seq[JsValue]].map(sequence => sequence.toList.map {
      //        jsvalue => Article.articleReader.reads(jsvalue)
      //      }
      (JsPath \ "listArticles").read[List[Article]]
    ) (Influence.apply _)

  implicit val influenceWriter = new Writes[Influence] {
    def writes(influence: Influence): JsObject = Json.obj(
      "totalNbLikes" -> influence.totalNbLikes,
      "nbFollowers" -> influence.nbFollowers,
      "nbFollowings" -> influence.nbFollowings,
      "nbArticles" -> influence.nbArticles,
      "lkesPerArticles" -> influence.listArticles.map {
        article => Article.articleWriter.writes(article)
      }
    )

  }

}
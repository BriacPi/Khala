package models


import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
  * Created by corpus on 19/02/2016.
  */
case class Article (
                   id: Option[Long],
                   author_id: Long,
                   creationDate: DateTime,
                   lastUpdate: DateTime,
                   title: String,
                   content: String,
                   nbModifications: Int = 0,
                   readingTime: Int
                   )

object Article {

  implicit val articleReader: Reads[Article] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "author_id").read[Long] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "title").read[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "nbModifications").read[Int] and
      (JsPath \ "readingTime").read[Int]
      
    ) (Article.apply _)

  implicit val articleWriter = new Writes[Article] {
  def writes(article: Article): JsObject = {
      def json = Json.obj(
        "author_id" -> article.author_id,
        "creationDate" -> article.creationDate,
        "lastUpdate" -> article.lastUpdate,
        "title" -> article.title,
        "content" -> article.content,
        "nbModifications" -> article.nbModifications,
        "readingTime" -> article.readingTime
      )
      article.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }
  }
}
package models


import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
  * Created by corpus on 19/02/2016.
  */
case class Article (
                   id: Option[Long],
//                   author_id: Long,
                   creationDate: DateTime,
                   lastUpdate: DateTime,
                   title: String,
                   content: String,
                   nbModifications: Int,
                   readingTime: Int,
                   tag1: String,
                   tag2: Option[String]
                   )

object Article {

  implicit val articleReader: Reads[Article] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "title").read[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "nbModifications").read[Int] and
      (JsPath \ "readingTime").read[Int] and
      (JsPath \ "tag1").read[String] and
      (JsPath \ "tag2").readNullable[String]
    ) (Article.apply _)

  implicit val articleWriter = new Writes[Article] {
    def writes(article: Article): JsObject = {
      def json = Json.obj(
        "creationDate" -> article.creationDate,
        "lastUpdate" -> article.lastUpdate,
        "title" -> article.title,
        "content" -> article.content,
        "nbModifications" -> article.nbModifications,
        "readingTime" -> article.readingTime,
        "tag1" -> article.tag1,
        "tag2" -> (article.tag2 match {
          case None => ""
          case Some(tag) => tag
        })
      )
      article.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }
  }

    def shorten(article: Article): Article = article.copy(content = article.content.take(140) + "...")


  def shorten(listArticle: List[Article]): List[Article] = {
    listArticle.map {
      shorten
    }
  }


}
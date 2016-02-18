package models

import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by corpus on 01/02/2016.
  */

// The implicit conversion has to be done ia long for dateTime and BSONDateTime
case class Article(
                    id: Option[String],
                    title: String,
                    content: String,
                    tag1: String,
                    tag2: Option[String],
                    creationDate: DateTime,
                    lastUpdate: DateTime,

                    // https://en.wikipedia.org/wiki/Words_per_minute: I tool 1150 CPM,
                    // Maybe adapt to languages?
                    readingTime: Int = 1,
                    nbLikes: Int = 0,
                    nbComments: Int = 0,
                    nbViews: Int = 0
                    //in DB:
                    //author_id = field "_id" in collection "users" but as string (so only the hex part)
                  ) {

}


object Article {
  implicit val articleReader: Reads[Article] = (
    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "title").read[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "tag1").read[String] and
      (JsPath \ "tag2").readNullable[String] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "readingTime").read[Int] and
      (JsPath \ "nbLikes").read[Int] and
      (JsPath \ "nbComments").read[Int] and
      (JsPath \ "nbViews").read[Int]
    ) (Article.apply _)

  implicit val articleWriter = new Writes[Article] {
    def writes(a: Article): JsObject = {

      def json = Json.obj(
        "title" -> a.title,
        "content" -> a.content,
        "readingTime" -> a.readingTime,
        "nbLikes" -> a.nbLikes,
        "nbComments" -> a.nbComments,
        "nbViews" -> a.nbViews,
        "creationDate" -> a.creationDate,
        "lastUpdate" -> a.lastUpdate,
        "tag1" -> a.tag1
      )
      a.id match {
        case None => {
          a.tag2 match {
            case None => json
            case Some(tag2) => json.++(Json.obj("tag2" -> tag2))
          }
        }
        case Some(id) => {
          a.tag2 match {
            case None => (Json.obj("_id" -> id)).++(json)
            case Some(tag2) => ((Json.obj("_id" -> id).++(json)).++(Json.obj("tag2" -> tag2)))
          }
        }
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
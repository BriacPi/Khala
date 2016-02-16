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
                    creationDate: DateTime,
                    lastUpdate: DateTime,
                    nbLikes: Int = 0,
                    nbComments: Int = 0,
                    nbViews: Int = 0
                    //in DB:
                    //author_id = field "_id" in collection "users" but as string (so only the hex part)


                  )

object Article {
  implicit val articleReader: Reads[Article] = (
    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "title").read[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "nbLikes").read[Int] and
      (JsPath \ "nbComments").read[Int] and
      (JsPath \ "nbViews").read[Int]
    ) (Article.apply _)

  implicit val articleWriter = new Writes[Article] {
    def writes(a: Article): JsObject = {

      def json = Json.obj(
        "title" -> a.title,
        "content" -> a.content,
        "nbLikes" -> a.nbLikes,
        "nbComments" -> a.nbComments,
        "nbViews" -> a.nbViews,
        "creationDate" -> a.creationDate,
        "lastUpdate" -> a.lastUpdate
      )
      a.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }


  }


}
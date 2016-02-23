package models


import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by corpus on 19/02/2016.
  */
case class Article(
                    id: Option[Long],
                    authorId: Long,
                    creationDate: DateTime,
                    lastUpdate: DateTime,
                    title: String,
                    summary: Option[String],
                    content: String,
                    nbModifications: Int,
                    readingTime: Int,
                    tag1: String,
                    tag2: Option[String],
                    status: String = "draft"
                  ){
  def this(articleUserEditable:ArticleUserEditable, authorId: Long, creationDate: DateTime, lastUpdate: DateTime,nbModifications: Int,
           readingTime: Int, status: String) = this(articleUserEditable.id, authorId,creationDate,lastUpdate,articleUserEditable.title,
    articleUserEditable.summary,articleUserEditable.content,nbModifications,readingTime,articleUserEditable.tag1,articleUserEditable.tag2,status)

}

object Article {

  implicit val articleReader: Reads[Article] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "author_id").read[Long] and
      (JsPath \ "creationDate").read[DateTime] and
      (JsPath \ "lastUpdate").read[DateTime] and
      (JsPath \ "title").read[String] and
      (JsPath \ "summary").readNullable[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "nbModifications").read[Int] and
      (JsPath \ "readingTime").read[Int] and
      (JsPath \ "tag1").read[String] and
      (JsPath \ "tag2").readNullable[String] and
      (JsPath \ "status").read[String]
    ) (Article.apply _)

  implicit val articleWriter = new Writes[Article] {
    def writes(article: Article): JsObject = {
      def json = Json.obj(
        "author_id" -> article.authorId,
        "creationDate" -> article.creationDate,
        "lastUpdate" -> article.lastUpdate,
        "title" -> article.title,
        "summary" -> article.summary.getOrElse[String](""),
        "content" -> article.content,
        "nbModifications" -> article.nbModifications,
        "readingTime" -> article.readingTime,
        "tag1" -> article.tag1,
        "tag2" -> article.tag2.getOrElse[String](""),
        "status" -> article.status
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

case class ArticleUserEditable(
                                id: Option[Long],
                                title: String,
                                summary: Option[String],
                                content: String,
                                tag1: String,
                                tag2: Option[String]
                              )
 {
   def this(article:Article) = this(article.id,article.title,article.summary,article.content,article.tag1,article.tag2)
 }
object ArticleUserEditable {
  implicit val articleUserEditableReader: Reads[ArticleUserEditable] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[Long] and
      (JsPath \ "title").read[String] and
      (JsPath \ "summary").readNullable[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "tag1").read[String] and
      (JsPath \ "tag2").readNullable[String]
    ) (ArticleUserEditable.apply _)

  implicit val articleUserEditableWriter = new Writes[ArticleUserEditable] {
    def writes(articleUserEditable: ArticleUserEditable): JsObject = {
      def json = Json.obj(
        "title" -> articleUserEditable.title,
        "summary" -> articleUserEditable.summary.getOrElse[String](""),
        "content" -> articleUserEditable.content,
        "tag1" -> articleUserEditable.tag1,
        "tag2" -> articleUserEditable.tag2.getOrElse[String]("")
      )
      articleUserEditable.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }
  }
}


case class ArticleNbs(
                         id: Long,
                         nbViews: Int,
                         nbLikes: Int,
                         nbComments: Int,
                         nbBookmarks: Int
                       )
case class ArticleStats(
                         article: Article,
                         nbViews: Int,
                         nbLikes: Int,
                         nbComments: Int,
                         nbBookmarks: Int
                       )

object ArticleStats {

  def fromArticle(article: Article, articleNbs: ArticleNbs): ArticleStats = {
    ArticleStats(article, articleNbs.nbViews,articleNbs.nbLikes,
      articleNbs.nbComments,articleNbs.nbBookmarks)
  }

  implicit val articleStatsWriter = new Writes[ArticleStats] {
    def writes(articleStats: ArticleStats): JsObject = {
      Json.obj(
        "article" -> articleStats.article,
        "nbViews" -> articleStats.nbViews,
        "nbLikes" -> articleStats.nbLikes,
        "nbComments" -> articleStats.nbComments,
        "nbBookmarks" -> articleStats.nbBookmarks
      )
    }

  }
}


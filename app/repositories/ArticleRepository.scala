package repositories

import java.io.Serializable
import models.Article
import play.api.libs.json.{JsObject, Json}
import repositories._
import scala.util.{Failure, Success}
import models._
import _root_.utils.MongoDBProxy
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 05/02/2016.
  */

trait ArticleRepository {

}


object ArticleRepository extends ArticleRepository {
  val collectionArticle: BSONCollection = MongoDBProxy.db.collection("articles")
  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")


  implicit object articleReader extends BSONDocumentReader[Article] {
    def read(doc: BSONDocument): Article = {
      //GetAs returns automatically an option of the type it wraps around
      val id = doc.getAs[BSONObjectID]("_id") match {
        case None => None
        case Some(id) =>
          Some(id.toString().substring(14, 38))
      }
      val title = doc.getAs[String]("title").get
      val content = doc.getAs[String]("content").get
      val tag1 = doc.getAs[String]("tag1").getOrElse("")
      val tag2 = doc.getAs[String]("tag2")
      val creationDate = doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())
      val lastUpdate = doc.getAs[BSONDateTime]("lastUpdate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())
      val nbLikes = doc.getAs[Int]("nbLikes").getOrElse(0)
      val nbComments = doc.getAs[Int]("nbComments").getOrElse(0)
      val nbViews = doc.getAs[Int]("nbViews").getOrElse(0)


      Article(id, title, content, tag1, tag2, creationDate, lastUpdate, nbLikes, nbComments, nbViews)
      //how to not to build them

    }
  }

  implicit object articleWriter extends BSONDocumentWriter[Article] {
    def write(a: Article): BSONDocument = {
      def doc: BSONDocument = BSONDocument(
        "title" -> a.title,
        "content" -> a.content,
        "nbLikes" -> a.nbLikes,
        "nbComments" -> a.nbComments,
        "nbViews" -> a.nbViews,
        "creationDate" -> BSONDateTime(a.creationDate.getMillis),
        "lastUpdate" -> BSONDateTime(a.lastUpdate.getMillis),
        "tag1" -> a.tag1
      )
      a.id match {
        case None => {
          a.tag2 match {
            case None => doc
            case Some(tag2) => doc.add("tag2" -> a.tag2)
          }
        }
        case Some(id) =>
          a.tag2 match {
            case None => doc.add("_id" -> id)
            case Some(tag2) => doc.add("_id" -> id, "tag2" -> a.tag2)
          }
      }
    }
  }

  //authenticated action only so I won't check the existence of the user.
  def save(author: User, article: Article): Future[Article] = {
    //user is supposed to be legitimate
    val query: BSONDocument = ArticleRepository.articleWriter.write(article)

    //Cannot user a generic article.id.getOrElse in a selector as it will force an id!
    article.id match {
      //the article is in creation
      case None =>
        val creationDate = BSONDateTime(DateTime.now().getMillis())
        val sameTitleSameAuthorQuery = BSONDocument("author_id" -> author.id.get, "title" -> article.title)
        val futureOptionSameTitleArticle = collectionArticle.find(sameTitleSameAuthorQuery).cursor[BSONDocument]().
          headOption
        futureOptionSameTitleArticle.flatMap {
          opt =>
            opt match {
              // Here we force the title to be different for the same user, otherwise nothing is done
              case Some(doc) => Future.successful(article)
              case None =>
                val insertQuery = query.add(BSONDocument(
                  "_id" -> BSONObjectID.generate,
                  "creationDate" -> creationDate,
                  "lastUpdate" -> creationDate,
                  "nbModification" -> 0,
                  "author_id" -> author.id.get))
                val future = collectionArticle.insert(insertQuery)
                future.onComplete {
                  case Failure(e) => throw e
                  case Success(writeResult) =>
                    println(s"successfully inserted article with result: $writeResult")
                }
                val futureOptionArticle: Future[Option[Article]] = getByAuthorAndTitle(author, article.title)
                futureOptionArticle.map { optionArticle =>
                  optionArticle match {
                    case None => article
                    case Some(newArticle) => newArticle
                  }
                }

            }
        }

      case Some(articleId) =>
        val selector = BSONDocument("_id" -> BSONObjectID(articleId))
        val modifier = BSONDocument("$set" -> query.add("lastUpdate" -> BSONDateTime(DateTime.now().getMillis())),
          "$inc" -> BSONDocument("nbModification" -> 1))
        val future = collectionArticle.update(selector, modifier)
        Future.successful(article)
    }

  }

  //Article id!
  def getById(id: String): Future[Option[Article]] = {
    val query = BSONDocument("_id" -> BSONObjectID(id))
    val futureOption: Future[Option[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().headOption
    val futureArticle: Future[Option[Article]] = futureOption.map(opt =>
      opt match {
        case None => None
        case Some(doc) =>
          Some(articleReader.read(doc))
      }
    )
    futureArticle
  }


  //Author email!


  def getByAuthor(authorId: String): Future[List[Article]] = {
    val query = BSONDocument("author_id" -> authorId)
    val futureOption: Future[List[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().collect[List]()
    futureOption.map(list =>
      list.map {
        doc => articleReader.read(doc)
      })

  }


  def getByAuthor(author: User): Future[List[Article]] = getByAuthor(author.id.get)

  def getByEmail(email: String): Future[List[Article]] = {
    val query = BSONDocument("email" -> email)
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption
    val futureArticle = futureOption.flatMap(option =>
      option match {
        case None => Future.successful(List())
        case Some(userDoc) => getByAuthor(UserRepository.userReader.read(userDoc))
      }
    )
    futureArticle
  }

  def getByAuthorAndTitle(author: User, title: String): Future[Option[Article]] = {
    val query = BSONDocument("author_id" -> author.id.get, "title" -> title)
    val sortQuery = BSONDocument("lastUpdate" -> -1)
    val futureOptionDoc = collectionArticle.find(query).sort(sortQuery).cursor[BSONDocument]().headOption

    futureOptionDoc.map {
      optionDoc =>
        optionDoc match {
          case None => None
          case Some(doc) => Some(ArticleRepository.articleReader.read(doc))
        }
    }
  }

  def getAllArticles(): Future[List[Article]] = {
    val query = BSONDocument()
    val futureList: Future[List[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().collect[List]()
    val futureArticles: Future[List[Article]] = futureList.map {
      list => {
        list.map {
          doc => articleReader.read(doc)
        }
      }
    }
    return futureArticles

  }

  def getByTitle(title: String): Future[List[Article]] = {
    val query = BSONDocument("title" -> title)
    val futureList: Future[List[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().collect[List]()

    futureList.map { list =>
      list.map { doc =>
        articleReader.read(doc)
      }

    }
  }

  def getOneIdByTitle(title: String): Future[Option[BSONObjectID]] = {
    val query = BSONDocument("title" -> title)
    val futureOption: Future[Option[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().headOption

    val futureId: Future[Option[BSONObjectID]] = futureOption.map {
      case None => None
      case Some(doc) => Some(doc.getAs[BSONObjectID]("_id").get)
    }

    return futureId
  }

  def getTopArticle(user: User, sortQuery: BSONDocument): Future[Option[Article]] = {
    val query = BSONDocument("author_id" -> user.id.get)
    val futureOptionTopArticle: Future[Option[BSONDocument]] = collectionArticle.find(query).
      sort(BSONDocument("nbViews" -> -1)).cursor[BSONDocument]().headOption
    futureOptionTopArticle.map {
      optionTopArticle => optionTopArticle match {
        case None => None
        case Some(topArticleDoc) => Some(articleReader.read(topArticleDoc))
      }
    }
  }

  def getTopArticleByViews(user: User): Future[Option[Article]] = {
    val sortQuery = BSONDocument("nbViews" -> -1)
    getTopArticle(user, sortQuery)
  }

  def getAuthor(articleId: String): Future[JsObject] = {
    val query = BSONDocument("author_id" -> BSONObjectID(articleId))
    val futureOptionArticle: Future[Option[BSONDocument]] = collectionArticle.find(query).
      cursor[BSONDocument]().headOption
    futureOptionArticle.flatMap {
      optionArticle => optionArticle match {
        case None => Future.successful(Json.obj("firstName" -> "",
          "lastName" -> ""))
        case Some(article) =>
          val authorId = article.getAs[String]("author_id").get
          val queryUser = BSONDocument("_id" -> BSONObjectID(authorId))
          collectionUser.find(queryUser).cursor[BSONDocument]().headOption.map {
            optionUser => optionUser match {
              case None => Json.obj("firstName" -> "",
                "lastName" -> "")
              case Some(user) => Json.obj("firstName" -> (user.getAs[String]("firstName").get),
                "lastName" -> (user.getAs[String]("lastName").get))
            }
          }
      }

    }
  }

  def getAuthor(article: Article): Future[JsObject] = getAuthor(article.id.get)
}








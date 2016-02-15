package repositories

import java.io.Serializable
import models.Article
import repositories._
import scala.util.{Failure, Success}
import models._
import utils.MongoDBProxy
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
      val id = doc.getAs[String]("_id")
      val title = doc.getAs[String]("title").get
      val content = doc.getAs[String]("content").get
      //
      //      val q = BSONDocument("_id" -> doc.getAs[BSONObjectID]("userId").get)
      //      val userDoc = collection.find(q).cursor[BSONDocument]().collect[List]().value.get.get.head
      //      val author = UserRepository.userReader.read(userDoc)

      val creationDate = doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())
      val lastUpdate = doc.getAs[BSONDateTime]("lastUpdate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())
      val nbLikes = doc.getAs[Int]("nbLikes").getOrElse(0)
      val nbComments = doc.getAs[Int]("nbComments").getOrElse(0)

      Article(id, title, content, creationDate, lastUpdate, nbLikes, nbComments)
      //how to not to build them

    }
  }

  implicit object articleWriter extends BSONDocumentWriter[Article] {
    def write(a: Article): BSONDocument = {
      def doc: BSONDocument = BSONDocument(
        "_id" -> a.id.get,
        "title" -> a.title,
        "content" -> a.content,
        "nbLikes" -> a.nbLikes,
        "nbComments" -> a.nbComments,
        "creationDate" -> BSONDateTime(a.creationDate.getMillis),
        "lastUpdate" -> BSONDateTime(a.lastUpdate.getMillis)
      )
      return doc
    }
  }


  def create(user: User, article: Article) = {
    //user is supposed to be legitimate
    val insertQuery: BSONDocument = ArticleRepository.articleWriter.write(article).add(BSONDocument(
      "author_id" -> BSONObjectID(user.id.get),
      "creationDate" -> BSONDateTime(DateTime.now().getMillis)
    ))
    val future = collectionArticle.insert(insertQuery)
    future.onComplete {
      case Failure(e) => throw e
      case Success(writeResult) =>
        println(s"successfully inserted article with result: $writeResult")
    }
  }

  //Article id!
  def getById(id: BSONObjectID): Future[Option[Article]] = {
    val query = BSONDocument("_id" -> id)
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

  def getById(id: String): Future[Option[Article]] = getById(BSONObjectID(id))

  //Author email!


  def getByAuthor(authorId: BSONObjectID): Future[List[Article]] = {
    val query = BSONDocument("author_id" -> authorId)
    val futureOption: Future[List[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().collect[List]()
    futureOption.map(list =>
      list.map {
        doc => articleReader.read(doc)
      })

  }

  def getByAuthor(authorId: String): Future[List[Article]] = getByAuthor(BSONObjectID(authorId))

  def getByAuthor(author: User): Future[List[Article]] = getByAuthor(author.id.get)

  def getByEmail(email: String): Future[List[Article]] = {
    val query = BSONDocument("email" -> email)
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption
    val futureArticle = futureOption.flatMap(option =>
      option match {
        case None => Future.successful(List())
        case Some(doc) => articleReader.read(doc)
          getByAuthor(doc.getAs[BSONObjectID]("_id").get)
      }
    )
    futureArticle
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

  //  def getId(article: Article): Future[Option[BSONObjectID]] = getId(article.title, article.creationDate)
  //
  //  def getId(title: String, date: DateTime): Future[Option[BSONObjectID]] = {
  //    val creationDate = BSONDateTime(date.getMillis)
  //    val query = BSONDocument(
  //      "title" -> title,
  //      "creationDate" -> creationDate
  //    )
  //    val futureOption: Future[Option[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().headOption
  //
  //    val futureId: Future[Option[BSONObjectID]] = futureOption.map {
  //      case None => None
  //      case Some(doc) => Some(doc.getAs[BSONObjectID]("_id").get)
  //    }
  //
  //    return futureId
  //  }

  def getOneIdByTitle(title: String): Future[Option[BSONObjectID]] = {
    val query = BSONDocument("title" -> title)
    val futureOption: Future[Option[BSONDocument]] = collectionArticle.find(query).cursor[BSONDocument]().headOption

    val futureId: Future[Option[BSONObjectID]] = futureOption.map {
      case None => None
      case Some(doc) => Some(doc.getAs[BSONObjectID]("_id").get)
    }

    return futureId
  }


}









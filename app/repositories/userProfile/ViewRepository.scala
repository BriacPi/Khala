package repositories.userProfile

import models.{View, Article, User}
import models.userProfile.UserInfoMinimal
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson._
import _root_.utils.MongoDBProxy

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 17/02/2016.
  */

trait ViewRepository {

}

object ViewRepository extends ViewRepository {
  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")
  val collectionArticle: BSONCollection = MongoDBProxy.db.collection("articles")
  val collectionView: BSONCollection = MongoDBProxy.db.collection("views")

  implicit object viewReader extends BSONDocumentReader[View] {
    def read(doc: BSONDocument): View = {

      val optionId: Option[String] = doc.getAs[BSONObjectID]("_id") match {
        case None => None
        case Some(id) => Some(id.toString().substring(14, 38))
      }
      //check the implicit conversion
      View(
        optionId,
        doc.getAs[String]("user_id").get,
        doc.getAs[String]("article_id").get,
        doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())

      )
    }
  }


  implicit object viewWriter extends BSONDocumentWriter[View] {
    def write(v: View): BSONDocument = {
      def doc: BSONDocument = BSONDocument(
        "user_id" -> v.user_id,
        "article_id" -> v.article_id,
        "creationDate" -> BSONDateTime(v.creationDate.getMillis)
      )
      v.id match {
        case None => doc
        case Some(id) =>
          doc.add("_id" -> BSONObjectID(id))
      }
    }
  }


  def getByArticle(id: String): Future[List[View]] = {
    val query = BSONDocument("_id" -> BSONObjectID(id))
    val futureOptionViews: Future[List[BSONDocument]] = collectionView.find(query).cursor[BSONDocument]().collect[List]()
    futureOptionViews.map {
      list => list.map {
      viewDoc => viewReader.read(viewDoc)
      }
    }

  }

  def getByArticle(article: Article): Future[List[View]] = getByArticle(article.id.get)
}
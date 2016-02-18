package repositories

import scala.util.{Failure, Success}
import models.Tag
import utils.MongoDBProxy
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
import reactivemongo.bson._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 18/02/2016.
  */
trait TagRepository {

}

object TagRepository extends TagRepository {

  val collectionTag: BSONCollection = MongoDBProxy.db.collection("tags")

  implicit object tagReader extends BSONDocumentReader[Tag] {
    def read(doc: BSONDocument): Tag = {
      //check the implicit conversion
      Tag(
        doc.getAs[String]("name").get,
        doc.getAs[Int]("nbArticles").get,
        doc.getAs[Int]("nbFollowers").get,
        doc.getAs[String]("urlImage").getOrElse[String]("/img/profile_default_large"),
        doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value))
          .getOrElse[DateTime](DateTime.now())
      )
    }
  }

  implicit object tagWriter extends BSONDocumentWriter[Tag] {

    def write(tag: Tag): BSONDocument = {
      BSONDocument(
        "name" -> tag.name,
        "nbArticles" -> tag.nbArticles,
        "nbFollowers" -> tag.nbFollowers,
        "urlImage" -> tag.urlImage,
        "creationDate" -> BSONDateTime(tag.creationDate.getMillis())
      )

    }
  }

  def tagExists(s: String) = {
    val query = BSONDocument("name" -> s.toLowerCase())
    collectionTag.find(query).cursor[BSONDocument]().headOption.map {
      opt => opt match {
        case None => false
        case Some(tag) => true
      }
    }
  }

  def getTag(s: String): Future[Option[Tag]] = {
    val query = BSONDocument("name" -> s.toLowerCase())
    collectionTag.find(query).cursor[BSONDocument]().headOption.map {
      opt => opt match {
        case None => None
        case Some(tagDoc) => Some(tagReader.read(tagDoc))
      }
    }
  }

  def create(tagName: String): Future[Option[Tag]] = {
    tagExists(tagName).flatMap {
      bool => if (bool) getTag(tagName)
      else {
        val query = BSONDocument("name" -> tagName.toLowerCase())
        val future = collectionTag.insert((query))
        future.onComplete {
          case Failure(e) => throw e
          case Success(writeResult) => "Tag insertion successful"
        }
        getTag(tagName)
      }
    }
  }


}
package repositories.userProfile

import _root_.utils.MongoDBProxy
import models.{User, Tagging}
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.CountCommand
import reactivemongo.bson._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


// BSON implementation of the count command
import reactivemongo.api.commands.bson.BSONCountCommand.{Count, CountResult}
import reactivemongo.bson.{BSONArray, BSONDocument}
import reactivemongo.api.BSONSerializationPack
import reactivemongo.api.commands.Command


/**
  * Created by corpus on 17/02/2016.
  */
trait TaggingRepository {

}

object TaggingRepository extends TaggingRepository {

  val collectionArticle: BSONCollection = MongoDBProxy.db.collection("articles")
  val collectionTagging: BSONCollection = MongoDBProxy.db.collection("taggings")

  implicit object TaggingReader extends BSONDocumentReader[Tagging] {
    def read(doc: BSONDocument): Tagging = {

      val optionId: Option[String] = doc.getAs[BSONObjectID]("_id") match {
        case None => None
        case Some(id) => Some(id.toString().substring(14, 38))
      }
      //check the implicit conversion
      Tagging(
        optionId,
        doc.getAs[String]("tag_name").get,
        doc.getAs[String]("author_id").get,
        doc.getAs[String]("article_id").get,
        doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value)).getOrElse(DateTime.now())

      )
    }
  }


  implicit object TaggingWriter extends BSONDocumentWriter[Tagging] {
    def write(t: Tagging): BSONDocument = {
      def doc: BSONDocument = BSONDocument(
        "tag_name" -> t.tag_name,
        "author_id" -> t.author_id,
        "article_id" -> t.article_id,
        "creationDate" -> BSONDateTime(t.creationDate.getMillis)
      )
      t.id match {
        case None => doc
        case Some(id) =>
          doc.add("_id" -> BSONObjectID(id))
      }
    }
  }

  def getNbArticlesByTagByAuthor(id: String): Future[BSONDocument] = {
    val commandDoc = BSONDocument(
      "aggregate" -> "taggings",
      "pipeline" -> BSONArray(
        BSONDocument("$match" -> BSONDocument("author_id" -> id)),
        BSONDocument(
          "$group" -> BSONDocument(
            "_id" -> "$tag_name",
            "totalNbArticles" -> BSONDocument("$sum" -> 1))),
        BSONDocument("$sort" -> BSONDocument("total" -> -1))
      )
    )

    val runner = Command.run(BSONSerializationPack)

    val futureResult: Future[BSONDocument] =
      runner.apply(MongoDBProxy.db, runner.rawCommand(commandDoc)).one[BSONDocument]
    futureResult
  }


  def getNbArticlesByTagByAuthor(user: User): Future[BSONDocument] = getNbArticlesByTagByAuthor(user.id.get)

}

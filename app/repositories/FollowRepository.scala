package repositories

import models.{Follow, Tag}
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocumentWriter, BSONDateTime, BSONDocument, BSONDocumentReader}
import utils.MongoDBProxy

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 18/02/2016.
  */
trait FollowRepository {

}

object FollowRepository extends FollowRepository {


  val collectionFollow: BSONCollection = MongoDBProxy.db.collection("follows")

  implicit object followReader extends BSONDocumentReader[Follow] {
    def read(doc: BSONDocument): Follow = {
      //check the implicit conversion
      Follow(
        doc.getAs[String]("_id"),
        doc.getAs[String]("follower_id").get,
        doc.getAs[String]("author_id").get,
        doc.getAs[BSONDateTime]("creationDate").map(dt => new DateTime(dt.value))
          .getOrElse[DateTime](DateTime.now())
      )
    }
  }

  implicit object followWriter extends BSONDocumentWriter[Follow] {

    def write(follow: Follow): BSONDocument = {
      BSONDocument(
        "_id" -> follow.id,
        "follower_id" -> follow.follower_id,
        "author_id" -> follow.author_id,
        "creationDate" -> BSONDateTime(follow.creationDate.getMillis())
      )

    }
  }


  def hasFollowed(followerId: String, authorId: String): Future[Boolean] = {
    val query = BSONDocument("follower_id" -> followerId, "author_id" -> authorId)
    collectionFollow.find(query).cursor[BSONDocument]().headOption.map {
      opt => opt match {
        case None => false
        case Some(follow) => true
      }
    }

  }

  def getFollow(followerId: String, authorId: String): Future[Option[Follow]] = {
    val query = BSONDocument("follower_id" -> followerId, "author_id" -> authorId)
    collectionFollow.find(query).cursor[BSONDocument]().headOption.map {
      opt => opt match {
        case None => None
        case Some(followDoc) => Some(followReader.read(followDoc))
      }
    }
  }

  def followsOrUnfollows(followerId: String, authorId: String): Future[String] = {
    val query = BSONDocument("follower_id" -> followerId, "author_id" -> authorId)
    getFollow(followerId, authorId).map {
      opt => opt match {
        //Unfollowing
        case None =>
          collectionFollow.remove(query)
          "follow.remove.success"
        case Some(follow) => val insertQuery = query.add("crationDate" -> BSONDateTime(DateTime.now().getMillis()))
          collectionFollow.insert(insertQuery)
          getFollow(followerId, authorId)
          "follow.add.success"
      }
    }

  }
}
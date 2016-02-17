package repositories

import java.io.Serializable


import models.Article
import reactivemongo.api.commands.WriteResult

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models._
import utils.MongoDBProxy
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson._

// BSON implementation of the count command
import reactivemongo.api.commands.bson.BSONCountCommand.{Count, CountResult}

// BSON serialization-deserialization for the count arguments and result
import reactivemongo.api.commands.bson.BSONCountCommandImplicits._

/**
  * Created by corpus on 11/02/2016.
  */
trait LikeRepository {

}

object LikeRepository extends LikeRepository {
  val collectionLike: BSONCollection = MongoDBProxy.db.collection("likes")
  val collectionArticle: BSONCollection = MongoDBProxy.db.collection("articles")


  def hasLiked(userId: String, articleId: String): Future[Boolean] = {

    val query = BSONDocument("user_id" -> userId, "article_id" -> articleId)
    val command = Count(query)
    val result: Future[CountResult] = collectionLike.runCommand(command)

    result.map { res =>
      val numberOfDocs: Int = res.value
      if (numberOfDocs == 0) false
      else true
    }
  }
    def createOrRemove(userId: String, articleId: String): Future[String] = {
      val selector = BSONDocument("article_id" -> articleId)
      val query = selector.add(BSONDocument("user_id" -> userId))
      val futureBool = hasLiked(userId, articleId)

      futureBool.map { bool =>

        if (bool) {
          val modifier = BSONDocument("$inc" -> BSONDocument("nbLikes" -> -1))
          collectionArticle.update(selector, modifier)
          def future: Future[WriteResult] = collectionLike.remove(query)
          future.onComplete {
            case Failure(e) => throw e
            case Success(writeResult) =>
          }
          "like.remove.success"
        }
        else {
          val modifier = BSONDocument("$set" -> BSONDocument("creationDate" -> BSONDateTime(DateTime.now().getMillis())),
            "$inc" -> BSONDocument("nbLikes" -> 1))
          collectionArticle.update(selector, modifier)

          def future: Future[WriteResult] = collectionLike.insert(query)
          future.onComplete {
            case Failure(e) => throw e
            case Success(writeResult) =>
          }
          "like.add.success"
        }
      }
    }


    def createOrRemove(user: User, article: Article): Future[String] = createOrRemove(user.id.get, article.id.get)

    //already have likes
    def getNumberLikes(article: Article): Future[Int] = {

      val futureOptionArticle: Future[Option[Article]] = ArticleRepository.getById(article.id.getOrElse(""))

      val futureNumberArticles: Future[Int] = futureOptionArticle.flatMap {
        case None => Future {
          0
        }
        case Some(articleFound) => {
          val query = BSONDocument("article_id" -> BSONObjectID(articleFound.id.get))
          val command = Count(query)
          val result: Future[CountResult] = collectionLike.runCommand(command)

          result.map { res =>
            val numberArticles = res.value
            numberArticles
          }
        }
      }
      return futureNumberArticles
    }

    def getLikesByTitle(title: String): Future[Int] = {
      val futureArticleOptionId: Future[Option[BSONObjectID]] = ArticleRepository.getOneIdByTitle(title)

      val futureNumberArticles: Future[Int] = futureArticleOptionId.flatMap {
        case None => Future {
          0
        }

        case Some(articleId) => {

          val query = BSONDocument("article_id" -> articleId)
          val command = Count(query)
          val result: Future[CountResult] = collectionLike.runCommand(command)

          result.map { res =>
            val numberArticles = res.value
            numberArticles
          }
        }
      }
      return futureNumberArticles
    }
  }


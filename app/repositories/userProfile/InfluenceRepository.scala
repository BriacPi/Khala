package repositories.userProfile

import models.{Article, User}
import reactivemongo.api.collections.bson.BSONCollection
import repositories.{ArticleRepository, LikeRepository, UserRepository}
import utils.MongoDBProxy

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by corpus on 15/02/2016.
  */
trait InfluenceRepository {

}

object InfluenceRepository extends InfluenceRepository {

  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")
  val collectionLike: BSONCollection = MongoDBProxy.db.collection("likes")
  val collectionArticle: BSONCollection = MongoDBProxy.db.collection("articles")


  def getByUser(user: User) = {

    val futureListArticles: Future[List[Article]] = ArticleRepository.getByAuthor(user)
    val futureNbLikes: Future[List[List[Object]]] = futureListArticles.map {
      list => list.map {
        article => List(article, LikeRepository.getNumberLikes(article))
      }
    }

  }
}

//  def getByEmail(email: String) = {
//    val futureOptionUser: Future[Option[User]] = UserRepository.getByEmail(email)
//    val futureListArticles: Future[List[Article]] = ArticleRepository.getByEmail(email)
////    val futureNbFollowers
//
//    //check if we need to flatMap it, in which case they come in couples.
//    val futureNbLikes: Future[List[List[Object]]] = futureListArticles.map {
//      list => list.map {
//        article => List(article,LikeRepository.getNumberLikes(article))
//      }
//    }
//  }
//}

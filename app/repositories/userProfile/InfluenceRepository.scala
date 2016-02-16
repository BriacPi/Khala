package repositories.userProfile

import models.userProfile.Influence
import models.User
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONObjectID, BSONDocument, BSONDocumentReader}
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



  def getByUser(user: User) {

    val query = BSONDocument("_id"->BSONObjectID(user.id.get))
    val futureOptionUserDoc: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption
    futureOptionUserDoc.flatMap( optionUser =>
      optionUser match {
      case None => Future.successful(None)
      case Some(userDoc) => ArticleRepository.getByAuthor(user).map { list =>
        Influence(
          //mock
          0,
          userDoc.getAs[Int]("nbFollowers").get,
          userDoc.getAs[Int]("nbFollowings").get,
          0,
          list
        )
      }
    })

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

}
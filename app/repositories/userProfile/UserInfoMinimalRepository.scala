package repositories.userProfile

import models.User
import models.userProfile.userInfoMinimal
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument
import repositories.UserRepository

import utils.MongoDBProxy

import scala.concurrent.Future

/**
  * Created by corpus on 14/02/2016.
  */
trait UserInfoMinimalRepository {

}

object UserInfoMinimalRepository extends UserInfoMinimalRepository {

  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")

  def getByEmail(email: String): Future[Option[userInfoMinimal]] = {
    val query = BSONDocument {
      "email" -> email
    }
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption
    futureOption.map {
      case None => None
      case Some(userDoc) => Some(userInfoMinimal(
        userDoc.getAs[String]("email").get,
        userDoc.getAs[String]("firstName").getOrElse("error.noFirstName"),
        userDoc.getAs[String]("lastName").getOrElse("error.noLastName"),
        userDoc.getAs[String]("headline").getOrElse("error.noHeadline"),
        "Personal Information",
        "Payment Information"
      ))
    }
  }

  def getByUser(user: User): Future[Option[userInfoMinimal]] = getByEmail(user.email)


}

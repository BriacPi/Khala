package repositories.userProfile

import models.User
import models.userProfile.UserInfoMinimal
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONObjectID, BSONDocumentReader, BSONDocument}

import utils.MongoDBProxy

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by corpus on 14/02/2016.
  */
trait UserInfoMinimalRepository {

}

object UserInfoMinimalRepository extends UserInfoMinimalRepository {

  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")

  implicit object userInfoMinimalReader extends BSONDocumentReader[UserInfoMinimal] {
    def read(doc: BSONDocument): UserInfoMinimal = {

      val optionId = doc.getAs[BSONObjectID]("_id") match {
        case None => None
        case Some(id) => Some(id.toString())
      }
      //check the implicit conversion
      UserInfoMinimal(
        doc.getAs[String]("email").get,
        doc.getAs[String]("firstName").getOrElse("error.noFirstName"),
        doc.getAs[String]("lastName").getOrElse("error.noLastName"),
        doc.getAs[String]("headline").getOrElse("error.noHeadline"),
        "Generic photo URL" + optionId.getOrElse("Anonymous"),
        "Generic Personal Information URL" + optionId.getOrElse("Anonymous"),
        "Generic Payment Information URL" + optionId.getOrElse("Anonymous")
      )
    }
  }

  def getByEmail(email: String): Future[Option[UserInfoMinimal]] = {
    val query = BSONDocument {
      "email" -> email
    }
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption
    futureOption.map {
      case None => None
      case Some(doc) => Some(UserInfoMinimalRepository.userInfoMinimalReader.read(doc))
    }
  }

  def getByUser(user: User): Future[Option[UserInfoMinimal]] = getByEmail(user.email)


}

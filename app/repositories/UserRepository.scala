package repositories

import reactivemongo.api.collections.bson
import repositories._
import scala.util.{Failure, Success}
import models.User
import utils.MongoDBProxy
import org.joda.time.DateTime
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}
import reactivemongo.bson._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by corpus on 05/02/2016.
  */
trait UserRepository {

}

object UserRepository extends UserRepository {
  val collectionUser: BSONCollection = MongoDBProxy.db.collection("users")

  implicit object userReader extends BSONDocumentReader[User] {
    def read(doc: BSONDocument): User = {

      val optionId = doc.getAs[BSONObjectID]("_id") match {
        case None => None
        case Some(id) => Some(id.toString().substring(14, 38))
      }
      //check the implicit conversion
      User(optionId,
        doc.getAs[String]("email").get,
        doc.getAs[Boolean]("emailConfirmed").get,
        doc.getAs[String]("firstName").getOrElse("error.noFirstName"),
        doc.getAs[String]("lastName").getOrElse("error.noLastName"),
        doc.getAs[String]("password").get,
        doc.getAs[List[String]]("services").get
      )
    }
  }

  implicit object userWriter extends BSONDocumentWriter[User] {

    def write(user: User): BSONDocument = {
      val doc = BSONDocument(
        "email" -> user.email,
        "emailConfirmed" -> user.emailConfirmed,
        "firstName" -> user.firstName,
        "lastName" -> user.lastName,
        "password" -> user.password,
        "services" -> user.services
      )
      user.id match {
        case None => doc
        case Some(id) => doc.add(BSONDocument("_id" -> BSONObjectID(id)))
      }
    }
  }

  def save(user: User): Future[User] = {
    val futureOptionExistingUser: Future[Option[User]] = getByEmail(user.email)
    user.id match {

      //the user is in creation
      case None => {
        futureOptionExistingUser.flatMap { optionExistingUser =>
          optionExistingUser match {
            case None =>
              val newUser: BSONDocument = userWriter.write(user).add(BSONDocument(
                "_id" -> BSONObjectID.generate,
                "nbFollowers" -> 0,
                "nbFollowings" -> 0,
                "urlPhotoProfile" -> "/img/profile_default_large",
                "registrationDate" -> BSONDateTime(DateTime.now().getMillis())))
              val future = collectionUser.insert(newUser)
              val futureOptionUser = getByEmail(user.email)
              futureOptionUser.map { optionUser =>
                optionUser match {
                  case None => user
                  case Some(newUser) => newUser
                }
              }
              Future.successful(user)

            //the email is already in use
            case Some(existingUser) =>
              Future.failed(new Throwable("error.emailInUse.text"))
          }
        }
      }
      case Some(userId) => {
        val modifier = UserRepository.userWriter.write(user)
        val selector = BSONDocument("_id" -> BSONObjectID(userId))
        futureOptionExistingUser.flatMap { optionExistingUser =>

          optionExistingUser match {
            // set is very important, otherwise it is a replacement!!!
            case None => collectionUser.update(selector, BSONDocument("$set" -> modifier), upsert = false)
              return Future.successful(user)
            case Some(existingUser) =>
              if (existingUser.id.get == userId) {
                collectionUser.update(selector, BSONDocument("$set" -> modifier), upsert = false)
                return Future.successful(user)
              }
              else
                return Future.failed(new Throwable("error.emailInUse.text"))
          }
        }
      }
    }
  }

  def editPassword(user: User): Unit = {

    val selector = BSONDocument("email" -> user.email)
    val modifier = BSONDocument("$set" -> BSONDocument("password" -> user.password))
    val future = collectionUser.update(selector, modifier)
    future.onComplete {
      case Failure(e) => throw e
      case Success(writeResult) =>
        println(s"successfully updated document with result: $writeResult")
    }
  }

  def remove(email: String): Unit = {

    val selector = BSONDocument("email" -> email)
    val future = collectionUser.remove(selector)
    future.onComplete {
      case Failure(e) => throw e
      case Success(writeResult) => println("successfully removed document")
    }
  }

  def remove(user: User): Unit = UserRepository.remove(user.email)


  def getByEmail(email: String): Future[Option[User]] = {
    val query = BSONDocument("email" -> email)
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption

    val futureUser: Future[Option[User]] = futureOption.map {
      case Some(doc) => Some(userReader.read(doc))
      case _ => None
    }
    return futureUser
  }

  def getByName(name: String): Future[List[User]] = {

    //IntelliJi doesn't solve BSONCollection type, has to state it
    val query = BSONDocument("$or" -> BSONDocument("firstName" -> name, "lastName" -> name))

    //value gives option of try from future list, 2 gets fot option and try then head
    //collect returns a future type that needs to be unwrapped
    val listBSON: Future[List[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().collect[List]()

    listBSON.map {
      listDoc => listDoc.map {
        doc => UserRepository.userReader.read(doc)
      }
    }
  }
  def getPhotoProfileUrl(userId: String): Future[String] = {

    val query = BSONDocument("_id" -> BSONObjectID(userId))
    collectionUser.find(query).cursor[BSONDocument]().headOption.map {
      opt => opt match {
        case None => ""
        case Some(userDoc) => userDoc.getAs[String]("urlPhotoProfile").get
      }
    }

  }
}

package repositories

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
      User(doc.getAs[String]("email").get,
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
      BSONDocument(
        "email" -> user.email,
        "emailConfirmed" -> user.emailConfirmed,
        "firstName" -> user.firstName,
        "lastName" -> user.lastName,
        "password" -> user.password,
        "services" -> user.services

      )

    }
  }


  def create(user: User): Unit = {
    val userDoc: BSONDocument = userWriter.write(user)

    // add or ++ methods create a new copy, deos not append.
    //    val newUserDoc = user.add("dateRegistration" -> BSONDateTime(DateTime.now().getMillis))
    val future: Future[WriteResult] = collectionUser.insert(userDoc)
    future.onComplete {
      case Failure(e) => throw e
      case Success(writeResult) =>
        println(s"successfully created user with result: $writeResult")
    }
  }

  //  def editUser(user: EditUser): Unit = {
  //
  //    val selector = BSONDocument("email" -> user.email)
  //    val modifier = BSONDocument("$set" -> userWriter.write(user))
  //    val future = collectionUser.update(selector, modifier)
  //    future.onComplete {
  //      case Failure(e) => throw e
  //      case Success(writeResult) =>
  //        println(s"successfully edited user with result: $writeResult")
  //    }
  //  }

  //  def save(modifier: BSONDocument, selector: BSONDocument): Unit = {
  //    //don't want to insert if not found
  //    collectionUser.update(modifier, selector, upsert = true)
  //
  //  }

  def save(user: User): Future[User] = {
    val futureId = getId(user)
    futureId.map {
      case None => {
        val newUser: BSONDocument = userWriter.write(user).add(BSONDocument("registrationDate" -> BSONDateTime(DateTime.now().getMillis())))
        collectionUser.insert(newUser)
        user
      }
      case Some(userId) => {
        val modifier = UserRepository.userWriter.write(user)
        val selector = BSONDocument("_id" -> userId.getAs[BSONObjectID]("user_id").get)
        collectionUser.update(selector, modifier, upsert = true)
        user
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

  def getByName(name: String): Option[List[User]] = {
    //IntelliJi doesn't solve BSONCollection type, has to state it

    val query = BSONDocument("$or" -> BSONDocument("firstName" -> name, "lastName" -> name))

    //value gives option of try from future list, 2 gets fot option and try then head
    val listBSON = collectionUser.find(query).cursor[BSONDocument]().collect[List]().value.get.get
    //collect returns a future type that needs to be unwrapped
    if (listBSON.isEmpty) return None
    else Some((listBSON.map(doc => UserRepository.userReader.read(doc))))

  }

  def getId(email: String): Future[Option[BSONDocument]] = {
    val query = BSONDocument(
      "email" -> email
    )
    val futureOption: Future[Option[BSONDocument]] = collectionUser.find(query).cursor[BSONDocument]().headOption

    val futureId: Future[Option[BSONDocument]] = futureOption.map {
      case None => None
      case Some(doc) => Some(BSONDocument("user_id" -> doc.getAs[BSONObjectID]("_id")))
    }
    return futureId
  }

  def getId(user: User): Future[Option[BSONDocument]] = getId(user.email)
}

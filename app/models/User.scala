package models


import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import utils.silhouette.IdentitySilhouette


case class User(
                 id: Option[Long] = None,
                 email: String,
                 emailConfirmed: Boolean,
                 firstName: String,
                 lastName: String,
                 password: String,
                 services: String = "user",
                 registrationDate: DateTime
               ) extends IdentitySilhouette {
  def key = email

  def fullName: String = firstName + " " + lastName
}

object User {

  implicit val userReader: Reads[User] = (
    //readNullable manages option
    (JsPath \ "id").readNullable[Long] and
      (JsPath \ "email").read[String] and
      (JsPath \ "emailConfirmed").read[Boolean] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "services").read[String] and
      (JsPath \ "registrationDate").read[DateTime]
    ) (User.apply _)

  implicit val userWriter = new Writes[User] {
    def writes(user: User): JsObject = {
      def json = Json.obj(
        "email" -> user.email,
        "emailConfirmed" -> user.emailConfirmed,
        "firstName" -> user.firstName,
        "lastName" -> user.lastName,
        "password" -> user.password,
        "services" -> user.services,
        "registrationDate" -> user.registrationDate
      )
      user.id match {
        case None => json
        case Some(id) => (Json.obj("_id" -> id)).++(json)

      }
    }
  }
}

case class AuthorNbs(
                      id: Long,
                      nbFollowers: Int,
                      nbArticles: Int
                    )

object AuthorNbs {

  implicit val authorNbsReader: Reads[AuthorNbs] = (
    //readNullable manages option
    (JsPath \ "id").read[Long] and
      (JsPath \ "nbFollowers").read[Int] and
      (JsPath \ "nbArticles").read[Int]
    ) (AuthorNbs.apply _)

  implicit val authorNbsWriter = new Writes[AuthorNbs] {
    def writes(authorNbs: AuthorNbs): JsObject = {
      Json.obj(
        "id" -> authorNbs.id,
        "nbFollowers" -> authorNbs.nbFollowers,
        "nbArticles" -> authorNbs.nbArticles
      )
    }
  }
}
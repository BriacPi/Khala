package models


import org.joda.time.DateTime
import play.api.libs.json._
import play.api.libs.functional.syntax._
import utils.silhouette.IdentitySilhouette


case class User(
                 id: Option[String] = None,
                 email: String,
                 emailConfirmed: Boolean,
                 firstName: String,
                 lastName: String,
                 password: String,
                 services: List[String]
               ) extends IdentitySilhouette {
  def key = email

  def fullName: String = firstName + " " + lastName
}

object User {

  implicit val userReader: Reads[User] = (
    //readNullable manages option
    (JsPath \ "_id").readNullable[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "emailConfirmed").read[Boolean] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "services").read[List[String]]
    ) (User.apply _)

  implicit val userWriter = new Writes[User] {
    def writes(user: User): JsObject = {
      def json = Json.obj(
        "email" -> user.email,
        "emailConfirmed" -> user.emailConfirmed,
        "firstName" -> user.firstName,
        "lastName" -> user.lastName,
        "password" -> user.password,
        "services" -> user.services
      )
      user.id match {
        case None => json
        case Some(id) =>(Json.obj("_id" -> id)).++(json)

      }
    }
  }
}
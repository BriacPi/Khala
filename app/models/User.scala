package models

import javax.swing.SpringLayout.Constraints

import org.joda.time.DateTime
import play.api.data.format.Formats
import play.api.libs.json._
import play.api.libs.functional.syntax._
import utils.silhouette.IdentitySilhouette
import com.mohiva.play.silhouette.impl.util.BCryptPasswordHasher
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.internal.util.TableDef.Column

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
      (JsPath \ "dateRegistration").read[List[String]]
    ) (User.apply _)

  implicit val userWriter = new Writes[User] {
    def writes(user: User): JsObject = Json.obj(
      "_id" -> user.id,
      "email" -> user.email,
      "emailConfirmed" -> user.emailConfirmed,
      "firstName" -> user.firstName,
      "lastName" -> user.lastName,
      "password" -> user.password,
      "services" -> user.services
    )
  }
}
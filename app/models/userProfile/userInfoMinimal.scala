package models.userProfile


import play.api.libs.json._
import play.api.libs.functional.syntax._


import reactivemongo.api.gridfs.{// ReactiveMongo GridFS
DefaultFileToSave, FileToSave, GridFS, ReadFile
}

case class UserInfoMinimal(
                            email: String,
                            firstName: String,
                            lastName: String,
                            headline: String,
                          //By defaut point to the anonymous head.
                            urlPhoto: String,
                            urlUserInfo: String,
                            urlPaymentInfo: String

                          )


object UserInfoMinimal {

  implicit val userInfoMinimal: Reads[UserInfoMinimal] = (
    (JsPath \ "email").read[String] and
      (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "headline").read[String] and
      (JsPath \ "urlPhoto").read[String] and
      (JsPath \ "urlUserInfo").read[String] and
      (JsPath \ "urlPaymentInfo").read[String]
    ) (UserInfoMinimal.apply _)

  implicit val userInfoMinimalWriter = new Writes[UserInfoMinimal] {
    def writes(userInfoMinimal: UserInfoMinimal): JsObject = Json.obj(
      "email" -> userInfoMinimal.email,
      "firstName" -> userInfoMinimal.firstName,
      "lastName" -> userInfoMinimal.lastName,
      "headline" -> userInfoMinimal.headline,
      "urlPhoto" -> userInfoMinimal.urlPhoto,
      "urlUserInfo" -> userInfoMinimal.urlUserInfo,
      "urlPaymentInfo" -> userInfoMinimal.urlPaymentInfo
    )

  }

}

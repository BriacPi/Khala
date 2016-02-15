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
                            //Photo
                            urlUserInfo: String,
                            urlPaymentInfo: String

                          )


object UserInfoMinimal {

  implicit val userReader: Reads[UserInfoMinimal] = (
    (JsPath \ "email").read[String] and
    (JsPath \ "firstName").read[String] and
      (JsPath \ "lastName").read[String] and
      (JsPath \ "headline").read[String] and
      (JsPath \ "urlUserInfo").read[String] and
      (JsPath \ "urlPaymentInfo").read[String]
    ) (UserInfoMinimal.apply _)

  implicit val userWriter = new Writes[UserInfoMinimal] {
    def writes(userInfoMinimal: UserInfoMinimal): JsObject = Json.obj(
      "email" -> userInfoMinimal.email,
      "firstName" -> userInfoMinimal.firstName,
      "lastName" -> userInfoMinimal.lastName,
      "headline" -> userInfoMinimal.headline,
      "urlUserInfo" -> userInfoMinimal.urlUserInfo,
      "urlPaymentInfo" -> userInfoMinimal.urlPaymentInfo
    )

  }

}

/**
  * Created by corpus on 13/02/2016.
  */

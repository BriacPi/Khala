package controllers

import models.{Article, User}
import models.userProfile.{Influence, UserInfoMinimal}
import play.api.libs.json.{Json, JsObject}
import play.api.mvc.{AnyContent, Action}
import repositories.userProfile.{InfluenceRepository, UserInfoMinimalRepository}
import repositories.{LikeRepository, UserRepository, ArticleRepository}
import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.ws.WSClient
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 13/02/2016.
  */
class UserProfileController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi) extends AuthenticationController with I18nSupport {

  def getUser() = SecuredAction{ implicit request => {
    Ok(User.userWriter.writes(request.identity))
  }
  }

  def getUserInfoMinimal(): Action[AnyContent] = SecuredAction.async { implicit request => {
    def futureOptionUserInfoMinimal: Future[Option[UserInfoMinimal]] = UserInfoMinimalRepository.getByUser(request.identity)
    futureOptionUserInfoMinimal.map {
      case None => Ok(Json.obj("userInfoMinimal" -> ""))
      case Some(userInfoMinimal) => Ok(UserInfoMinimal.userInfoMinimalWriter.writes(userInfoMinimal))
    }
  }
  }
//
//  def getInfluence() = SecuredAction.async { implicit request => {
//    def futureOptionInfluence: Future[List[Article]] = InfluenceRepository.getByUser(request.identity)
//    futureOptionInfluence.map {
//      case None => Ok(Json.obj("influence" -> ""))
//      case Some(influence) =>  Ok(Influence.influenceWriter.writes(influence))
//    }
//  }}

//  def getInfluence() = SecuredAction.async { implicit request => {
//
//    def futureOptionInfluence = UserInfoMinimalRepository.getByUser(request.identity)
//    futureOptionUserInfoMinimal.map {
//      case None => Ok(Json.obj("userInfoMinimal" -> ""))
//      case Some(userInfoMinimal) => Ok(UserInfoMinimal.userInfoMinimalWriter.writes(userInfoMinimal))
//    }
//  }}
}

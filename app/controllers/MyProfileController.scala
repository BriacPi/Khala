package controllers

import models.{AuthorNbs, AuthorFollowers, User}

import play.api.libs.json.{Json}
import play.api.mvc.{AnyContent, Action}

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.ws.WSClient
import repositories.{UserRepository}
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 13/02/2016.
  */
class MyProfileController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi)
  extends AuthenticationController with I18nSupport {

  //  def getUserInfoMinimal(): Action[AnyContent] = SecuredAction.async { implicit request => {
  //    def futureOptionUserInfoMinimal: Future[Option[UserInfoMinimal]] = UserInfoMinimalRepository.getByUser(request.identity)
  //    futureOptionUserInfoMinimal.map {
  //      case None => Ok(Json.obj("userInfoMinimal" -> ""))
  //      case Some(userInfoMinimal) => Ok(UserInfoMinimal.userInfoMinimalWriter.writes(userInfoMinimal))
  //    }
  //  }
  //  }

  //
  //  def getInfluence() = SecuredAction.async { implicit request => {
  //    def futureOptionInfluence = InfluenceRepository.getByUser(request.identity)
  //    futureOptionInfluence.map {
  //      case None => Ok(Json.obj("influence" -> ""))
  //      case Some(influence) => Ok(Influence.influenceWriter.writes(influence))
  //    }
  //  }
  //  }

  def getUser() = SecuredAction {
    implicit request => {
      UserRepository.getByEmail(request.identity.email) match {
        case None => Ok(Json.obj("message" -> "no user found"))
        case Some(user) => Ok(User.userWriter.writes(user))
      }
    }

  }

  def getMyProfileStats() = SecuredAction {
    implicit request => {
      UserRepository.getAuthorNbs(request.identity.id.get) match {
        case None => Ok(Json.obj("message" -> "no user found"))
        case Some(authorNbs) => Ok(AuthorNbs.authorNbsWriter.writes(authorNbs))
      }
    }
  }
}


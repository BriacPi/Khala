package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{AnyContent, Action}
import repositories.{FollowRepository, LikeRepository}
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by corpus on 18/02/2016.
  */
class RelationshipController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi)
  extends AuthenticationController with I18nSupport {

  def hasLiked(articleId: String): Action[AnyContent] = SecuredAction.async { implicit request => {
    val futureBool = LikeRepository.hasLiked(request.identity.id.get, articleId)
    futureBool.map { bool =>
      if (bool) Ok(Json.obj("hasLiked" -> "true"))
      else Ok(Json.obj("hasLiked" -> "false"))
    }
  }
  }

  def likesOrUnlikes(articleId: String): Action[AnyContent] = SecuredAction.async { implicit request => {
    LikeRepository.createOrRemove(request.identity.id.get, articleId).map {
      s => Ok(Json.obj("messages:" -> s))
    }
  }
  }

  def hasFollowed(authorId: String) = SecuredAction.async { implicit request => {
    val futureBool = FollowRepository.hasFollowed(request.identity.id.get,authorId)
    futureBool.map { bool =>
      if (bool) Ok(Json.obj("hasLiked" -> "true"))
      else Ok(Json.obj("hasLiked" -> "false"))
    }
  }
  }

  def followsOrUnfollows(authorId: String): Action[AnyContent] = SecuredAction.async { implicit request => {
    FollowRepository.followsOrUnfollows(request.identity.id.get, authorId).map {
      s => Ok(Json.obj("messages:" -> s))
    }
  }
  }

}

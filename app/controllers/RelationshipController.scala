

package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{AnyContent, Action}
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}
import scala.concurrent.ExecutionContext.Implicits.global
import repositories.Relationships.{BookmarkRepository, FollowRepository}


/**
  * Created by corpus on 18/02/2016.
  */
class RelationshipController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi)
  extends AuthenticationController with I18nSupport {
  //
  //  def hasLiked(articleId: String): Action[AnyContent] = SecuredAction.async { implicit request => {
  //    val futureBool = LikeRepository.hasLiked(request.identity.id.get, articleId)
  //    futureBool.map { bool =>
  //      if (bool) Ok(Json.obj("hasLiked" -> "true"))
  //      else Ok(Json.obj("hasLiked" -> "false"))
  //    }
  //  }
  //  }
  //
  //  def likesOrUnlikes(articleId: String): Action[AnyContent] = SecuredAction.async { implicit request => {
  //    LikeRepository.createOrRemove(request.identity.id.get, articleId).map {
  //      s => Ok(Json.obj("messages:" -> s))
  //    }
  //  }
  //  }
  //
  def hasFollowed(authorId: Long) = SecuredAction { implicit request => {
    val bool: Boolean = FollowRepository.hasFollowed(request.identity.id.get, authorId)
    Ok(Json.obj("hasFollowed" -> bool))
  }
  }

  def followsOrUnfollows(authorId: Long): Action[AnyContent] = SecuredAction {
    implicit request => {
      val s: String = FollowRepository.followsOrUnfollows(request.identity.id.get, authorId)
      Ok(Json.obj("messages:" -> s))
    }
  }

  def hasBookmarked(articleId: Long) = SecuredAction { implicit request => {
    val bool: Boolean = BookmarkRepository.hasBookmarked(request.identity.id.get, articleId)
    Ok(Json.obj("hasBookmarked" -> bool))
  }
  }

  def bookmarksOrUnbookmarks(articleId: Long): Action[AnyContent] = SecuredAction {
    implicit request => {
      val s: String = BookmarkRepository.bookmarksOrUnbookmarks(request.identity.id.get, articleId)
      Ok(Json.obj("messages:" -> s))
    }
  }

}

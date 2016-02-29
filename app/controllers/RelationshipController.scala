

package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{AnyContent, Action}
import repositories.UserRepository
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}
import scala.concurrent.ExecutionContext.Implicits.global
import repositories.Relationships.{ViewRepository, LikeRepository, BookmarkRepository, FollowRepository}


/**
  * Created by corpus on 18/02/2016.
  */
class RelationshipController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi)
  extends AuthenticationController with I18nSupport {


  def viewArticle(articleId: Long) = UserAwareAction {
    implicit request => {
      UserRepository.getAuthorByArticle(articleId) match {
        case None => Ok(Json.obj("viewArticle" -> "article.notFound"))
        case Some(author) =>
          request.identity match {
            case None => Ok(Json.obj("viewArticle" -> ViewRepository.create(0.toLong, author.id.get, articleId)))
            case Some(user) => Ok(Json.obj("viewArticle" -> ViewRepository.create(user.id.get, author.id.get, articleId)))
          }
      }
    }
  }

  def hasLiked(articleId: Long): Action[AnyContent] = SecuredAction { implicit request => {
    val bool: Boolean = LikeRepository.hasLiked(request.identity.id.get, articleId)
    Ok(Json.obj("hasLiked" -> bool))
  }
  }

  def hasFollowed(authorId: Long) = SecuredAction { implicit request => {
    val bool: Boolean = FollowRepository.hasFollowed(request.identity.id.get, authorId)
    Ok(Json.obj("hasFollowed" -> bool))
  }
  }

  def followUnfollow(authorId: Long): Action[AnyContent] = SecuredAction {
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

  def bookmarkUnbookmark(articleId: Long): Action[AnyContent] = SecuredAction {
    implicit request => {
      UserRepository.getAuthorByArticle(articleId) match {
        case None => Ok(Json.obj("messages" -> "article.notFound"))
        case Some(author) =>
          val s: String = BookmarkRepository.bookmarkUnbookmark(request.identity.id.get, author.id.get, articleId)
          Ok(Json.obj("messages:" -> s))
      }

    }
  }

  def likeUnlike(articleId: Long) = SecuredAction {
    implicit request => {
      UserRepository.getAuthorByArticle(articleId) match {
        case None => Ok(Json.obj("messages" -> "article.notFound"))
        case Some(author) =>
          request.identity.id match {
            case Some(id) => Ok(Json.obj("messages" -> LikeRepository.likesOrUnlikes(id, author.id.get,articleId)))
            case None => Ok(Json.obj("messages" -> "like.action.failure"))
          }
      }
    }
  }
}

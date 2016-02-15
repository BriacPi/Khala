package controllers

import javax.inject.Inject

import models.Article
import repositories.{LikeRepository, UserRepository, ArticleRepository}

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, _}
import play.api.libs.ws.WSClient
import play.api.mvc._
import reactivemongo.bson.{BSONObjectID, BSONDocument}
import utils.silhouette.{AuthenticationEnvironment, AuthenticationController}


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by corpus on 11/02/2016.
  */

class ContentController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi) extends AuthenticationController with I18nSupport {

  val articleTitleForm: Form[String] = Form(
    "title" -> nonEmptyText
  )

  def getAllArticles() = Action.async { implicit request => {
    val futureArticles: Future[List[Article]] = ArticleRepository.getAllArticles()
    val futureJson: Future[List[JsValue]] = futureArticles.map { list => list.map {
      article => Article.articleWriter.writes(article)
    }
    }
    futureJson.map { jsonList =>
      Ok(Json.obj("articles" -> jsonList))
    }
  }
  }

  def getArticlesByAuthor(): Action[AnyContent] = SecuredAction.async { implicit request => {
    val futureArticles: Future[List[Article]] = ArticleRepository.getByUser(request.identity)
    val futureJson: Future[List[JsValue]] = futureArticles.map { list => list.map {
      article => Article.articleWriter.writes(article)
    }
    }
    futureJson.map { jsonList =>
      Ok(Json.obj("articles" -> jsonList))
    }
  }
  }

  //  def articleToLike() = SecuredAction { implicit request => {
  //    Ok(views.html.demo(articleTitleForm))
  //  }
  //}

  def likes(article: Article): Action[AnyContent] = SecuredAction.async { implicit request => {
    val userFutureOptionId: Future[Option[BSONObjectID]] = UserRepository.getId(request.identity)
    val articleFutureOptionId: Future[Option[BSONObjectID]] = ArticleRepository.getId(article)

    userFutureOptionId.flatMap {
      case None => Future {
        Ok(Json.obj("error.messages:" -> "the user does not exist"))
      }
      case Some(userId) => {
        articleFutureOptionId.map {
          case None => Ok(Json.obj("error.messages:" -> "the article does not exist"))
          case Some(articleId) => {
            LikeRepository.create(
              userId, articleId)
            Ok(Json.obj("messages:" -> "the user successfully liked the article"))
          }
        }
      }
    }

  }

  }

  def likes(title: String) = SecuredAction.async { implicit request => {
    val userFutureOptionId: Future[Option[BSONObjectID]] = UserRepository.getId(request.identity)
    val articleFutureOptionId: Future[Option[BSONObjectID]] = ArticleRepository.getOneIdByTitle(title)

    userFutureOptionId.flatMap {
      case None => Future {
        Ok(Json.obj("error.messages:" -> "the user does not exist"))
      }
      case Some(userId) => {
        articleFutureOptionId.flatMap {
          case None => Future {
            Ok(Json.obj("error.messages:" -> "the article does not exist"))
          }
          case Some(articleId) => {
            val futureString = LikeRepository.create(
              userId, articleId)
            futureString.map(
              string => Ok(Json.obj("messages:" -> string))
            )

          }
        }
      }
    }

  }

  }

//  def getNbLikes(title: String) = Action.async { implicit request => {
//    val futureInt: Future[Int] = LikeRepository.getLikesByTitle(title);
//    futureInt.map { nbLikes =>
//      Ok(Json.obj("messages:" -> "there is" + nbLikes" likes for this article!"))
//    }
//  }
//  }

}
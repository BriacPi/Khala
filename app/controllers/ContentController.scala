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
    val futureArticles: Future[List[Article]] = ArticleRepository.getByAuthor(request.identity)
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

    val articleFutureOptionId: Future[Option[Article]] = ArticleRepository.getById(article.id.get)
    articleFutureOptionId.map {
      case None => Ok(Json.obj("error.messages:" -> "error.noArticleFound.text"))
      case Some(articleId) => {
        LikeRepository.create(
          request.identity.id.get, article.id.get)
        Ok(Json.obj("messages:" -> "the user successfully liked the article"))
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
package controllers

import javax.inject.Inject

import models.Article
import org.joda.time.DateTime
import repositories.{ViewRepository, LikeRepository, UserRepository, ArticleRepository}

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

  val newArticleForm: Form[Article] = Form(
    mapping(
      "id" -> ignored(None: Option[String]),
      "title" -> nonEmptyText,
      "content" -> nonEmptyText,
      "tag1" -> nonEmptyText,
      "tag2" -> optional(text),
      "creationDate" -> ignored(DateTime.now()),
      "lastUpdate" -> ignored(DateTime.now()),
      "readingTime" -> ignored(0),
      "nbLikes" -> ignored(0),
      "nbComments" -> ignored(0),
      "nbViews" -> ignored(0)
    )(Article.apply)(Article.unapply)
  )

  def article(articleID: String) = UserAwareAction { implicit request =>
    val fakeUserId = ""
    ViewRepository.viewArticle(fakeUserId, articleID)
    Ok(views.html.content.article(articleID))

  }

  def writeArticle() = SecuredAction { implicit request =>

    Ok(views.html.content.writeArticle(newArticleForm))
  }

  def saveArticle() = SecuredAction.async { implicit request => {

    newArticleForm.bindFromRequest.fold(
      error => {

        // Request payload is invalid.envisageable
        Future.successful(BadRequest(views.html.content.writeArticle(newArticleForm)))
      },
      article => {
        if (article.title.length() == 0) {
          Future.successful(Ok(Json.obj("message" -> "error.emptyTitle")))
        }
        else if (article.title.length() > 300) Future.successful(Ok(Json.obj("message" -> "error.titleTooLong")))
        else {
          ArticleRepository.save(request.identity, article)
          Future.successful(Ok(Json.obj("message" -> "article.updateSuccessful")))
        }
      }

    )
  }
  }

  def getAllArticles() = UserAwareAction.async { implicit request => {
    val futureArticles: Future[List[Article]] = ArticleRepository.getAllArticles()
    val futureJson: Future[List[JsValue]] = futureArticles.map { list => Article.shorten(list).map {
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

      article => println(article.creationDate)
        Article.articleWriter.writes(article)
    }
    }
    futureJson.map { jsonList =>
      Ok(Json.obj("articles" -> jsonList))
    }
  }
  }

  def getTopArticleByViews(): Action[AnyContent] = SecuredAction.async { implicit request => {
    val futureOptionTopArticle: Future[Option[Article]] = ArticleRepository.getTopArticleByViews(request.identity)
    futureOptionTopArticle.map {
      optionTopArticle => optionTopArticle match {
        case None => Ok(Json.obj("topArticle" -> "error.noArticleFound"))
        case Some(topArticle) => Ok(Article.articleWriter.writes(topArticle))
      }
    }
  }
  }

  def getAuthorMini(articleId: String): Action[AnyContent] = UserAwareAction.async {
    implicit request => {
      ArticleRepository.getAuthorMini(articleId).map {
        json => Ok(json)
      }
    }
  }

  def getAuthor(articleId: String): Action[AnyContent] = UserAwareAction.async {
    implicit request => {
      ArticleRepository.getAuthor(articleId).map {
        optionJson => optionJson match {
          case None => Ok(Json.obj("user" -> "user.notFound"))
          case Some(jsonAuthor) => Ok(jsonAuthor)
        }

      }
    }
  }


}
package controllers

import javax.inject.Inject

import org.joda.time.DateTime
import play.api.libs.iteratee.Enumeratee

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, _}
import play.api.libs.ws.WSClient
import play.api.mvc._
import utils.silhouette.{AuthenticationEnvironment, AuthenticationController}
import play.api.cache._
import play.api.mvc._
import javax.inject.Inject
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{ArticleStats, Article, User}
import repositories.{UserRepository, ViewRepository, ArticleRepository}


//
///**
//  * Created by corpus on 11/02/2016.
//  */
//
class ContentController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi,
                                                val cache: CacheApi) extends AuthenticationController with I18nSupport {

  val newArticleForm: Form[Article] = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),
      "creationDate" -> ignored(DateTime.now()),
      "lastUpdate" -> ignored(DateTime.now()),
      "title" -> nonEmptyText,
      "summary" -> optional(text),
      "content" -> nonEmptyText,
      "nbModifications" -> ignored(0),
      "readingTime" -> ignored(1),
      "tag1" -> nonEmptyText,
      "tag2" -> optional(text)
    )(Article.apply)(Article.unapply)
  )

  //
  //  def article(articleID: String) = UserAwareAction { implicit request =>
  //    val fakeUserId = ""
  //    ViewRepository.viewArticle(fakeUserId, articleID)
  //    Ok(views.html.content.article(articleID))
  //
  //  }
  //
  def writeArticle() = SecuredAction { implicit request =>

    Ok(views.html.content.writeArticle(newArticleForm))
  }

  def saveArticle() = SecuredAction { implicit request => {

    newArticleForm.bindFromRequest.fold(
      error => {

        // Request payload is invalid.envisageable
        BadRequest(views.html.content.writeArticle(newArticleForm))
      },
      article => {
        if (article.title.length() == 0) {
          Ok(Json.obj("message" -> "error.emptyTitle"))
        }
        else if (article.title.length() > 300) Ok(Json.obj("message" -> "error.titleTooLong"))
        else {
          ArticleRepository.save(request.identity, article)
          Ok(Json.obj("message" -> "article.updateSuccessful"))
        }
      }

    )
  }
  }

  def getAllArticles() = UserAwareAction { implicit request => {
    val listArticles = ArticleRepository.getAllArticles().map(Article.shorten)
    Ok(Json.obj("articles" -> listArticles.map(Article.articleWriter.writes)))
  }
  }


  def getArticleStatsByAuthor(): Action[AnyContent] = SecuredAction { implicit request => {
    val listJsons: List[JsObject] = ArticleRepository.getArticleStatsByAuthor(request.identity.id.get).map {
      articleStat => ArticleStats.articleStatsWriter.writes(articleStat)
    }
    Ok(Json.obj("articles" -> listJsons))
  }
  }

  def getTopArticleStatsByViews() = SecuredAction { implicit request => {
    val listJsons: List[JsObject] = ArticleRepository.getTopArticleStatsByViews().map {
      articleStats => ArticleStats.articleStatsWriter.writes(articleStats)
    }
    Ok(Json.obj("articles" -> listJsons))

  }
  }

  def viewArticle(articleId: Long) = UserAwareAction { implicit request => {
    request.identity match {
      case None => ViewRepository.create(0.toLong, articleId)
      case Some(user) => ViewRepository.create(user.id.get, articleId)
    }
    Ok(Json.obj("articles" -> "views successful"))

  }
  }

  def getAuthorByArticle(articleId: Long) = UserAwareAction {
    implicit request => {
      UserRepository.getAuthorByArticle(articleId) match {
        case None => Ok(Json.obj("user" -> "user.notFound"))
        case Some(author) => Ok(Json.obj("user" -> User.userWriter.writes(author)))
      }

    }
  }

  //
  //  def getTopArticlesByViews(first: Int, last: Int): Action[AnyContent] = UserAwareAction.async {
  //    implicit request => {
  //      val futureTopArticles: Future[Array[Article]] = cache.getOrElse[Future[Array[Article]]]("futureTopArticles") {
  //        ArticleRepository.getTopArticlesByViews(24)
  //      }
  //      futureTopArticles.map {
  //        topArticles => val size = topArticles.length
  //          def realLast = {
  //            if (last >= size) size - 1
  //            else last
  //          }
  //
  //          def realFirst = {
  //            if (first < 0) 0
  //            else first
  //          }
  //          if (realFirst >= size) Ok(Json.obj("error mesage" -> "no content"))
  //          else Ok(Json.obj("articles" -> topArticles.slice(realFirst, realLast)))
  //      }
  //
  //
  //    }
  //
  //  }
}
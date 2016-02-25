package controllers

import java.io.Serializable
import javax.inject.Inject

import org.joda.time.DateTime
import play.api.data.validation.Constraints._
import play.api.libs.iteratee.Enumeratee

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, _}
import play.api.libs.ws.WSClient
import play.api.mvc._
import repositories.Contents.ArticleRepository
import repositories.Relationships.{ViewRepository, LikeRepository}
import utils.silhouette.{AuthenticationEnvironment, AuthenticationController}
import play.api.cache._
import play.api.mvc._
import javax.inject.Inject
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{ArticleUserEditable, ArticleStats, Article, User}
import repositories.UserRepository

import scala.reflect.macros.compiler.Errors


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
      "author_id" -> ignored(0.toLong),
      "creationDate" -> ignored(DateTime.now()),
      "lastUpdate" -> ignored(DateTime.now()),
      "title" -> nonEmptyText,
      "summary" -> optional(text.verifying(maxLength(255))),
      "content" -> nonEmptyText,
      "nbModifications" -> ignored(0),
      "readingTime" -> ignored(1),
      "tag1" -> nonEmptyText.verifying(maxLength(100)),
      "tag2" -> optional(text.verifying(maxLength(100))),
      "status" -> ignored("draft")
    )(Article.apply)(Article.unapply)
  )

  def save() = SecuredAction(parse.json) { implicit request => {
    ArticleUserEditable.articleUserEditableReader.reads(request.body) match {
      case e: JsError => BadRequest("Expecting correct Article Json data")
      case s: JsSuccess[ArticleUserEditable] =>
        val article = new Article(s.get, request.identity.id.get, DateTime.now(),
          DateTime.now(), 0, math.max(s.get.content.length / 1150, 1), "draft")
        ArticleRepository.save(request.identity.id.get, article)
        Ok(Json.obj("article" -> request.body))
    }
  }
  }


  def publish(status: String) = SecuredAction(parse.json) { implicit request => {
    if (!(status == "public") || (status == "private")) {
      println(status + "1")
      BadRequest("Really, you don't want your story to be on Mars right?")
    }
    else {
      ArticleUserEditable.articleUserEditableReader.reads(request.body) match {
        case e: JsError => BadRequest("Expecting correct Article Json data")
        case s: JsSuccess[ArticleUserEditable] =>
          val article = new Article(s.get, request.identity.id.get, DateTime.now(),
            DateTime.now(), 0, math.max(s.get.content.length / 1150, 1), status)
          ArticleRepository.save(request.identity.id.get, article)
          ArticleRepository.publish(request.identity.id.get, article)
          Ok(Json.obj("article" -> request.body))

      }
    }
  }
  }

  def deleteArtricle(article: Article) = SecuredAction {
    implicit request => {
      Ok(Json.obj("delete.message" -> ArticleRepository.deleteArticle(request.identity.id.get, article)))
    }
  }


  def getEditable(articleId: Long) = SecuredAction { implicit request => {
    val optEditable: Option[ArticleUserEditable] = ArticleRepository.getEditable(request.identity.id.get, articleId)
    optEditable match {
      case None => BadRequest("The specified article or draft is not editable by current user.")
      case Some(editable) => Ok(Json.obj("article" -> ArticleUserEditable.articleUserEditableWriter.writes(editable)))
    }
  }
  }


  def getArticle(id: Long) = UserAwareAction {
    implicit request => {
      ArticleRepository.getById(id) match {
        case Some(article) => {
          if (article.status == "public") Ok(views.html.content.article(article))
          else BadRequest("Cannot visualize drafts")

        }

        case None => Redirect(routes.Application.index)
      }

    }
  }


  def write() = SecuredAction { implicit request =>
    ArticleRepository.getRecentUntitledDraft(request.identity.id.get) match {
      case None => {
        println("debug")
        val draftCreationDate = DateTime.now()
        val virginDraft = Article(None, request.identity.id.get, draftCreationDate,
          draftCreationDate, "", None, "", 0, 0, "", None, "draft")
        ArticleRepository.save(request.identity.id.get, virginDraft)
        val optId: Option[Long] = ArticleRepository.getIdByAuthorAndDate(request.identity.id.get, draftCreationDate)
        optId match {
          case None => BadRequest("Hum, something is not write.")
          case Some(id) => Ok(views.html.content.write(id))
        }
      }
      case Some(draft) => Ok(views.html.content.write(draft.id.get))
    }

  }


  def getAllArticles() = UserAwareAction {
    implicit request => {
      val listArticles: List[Article] = cache.getOrElse[List[Article]]("listAllArticles") {
        ArticleRepository.getAllArticles().map(Article.shorten)
      }
      cache.set("listAllArticle", cache.get("listAllArticles"), expiration = 10.minutes)
      Ok(Json.obj("articles" -> listArticles.map(Article.articleWriter.writes)))
    }
  }

  def getArticleStatsByAuthor(): Action[AnyContent] = SecuredAction {
    implicit request => {
      val listJsons = cache.getOrElse[List[JsObject]]("listJsonsByAuthor") {
        ArticleRepository.getArticleStatsByAuthor(request.identity.id.get).map {
          articleStat => ArticleStats.articleStatsWriter.writes(articleStat)
        }
      }

      cache.set("ListJsonsByAuthor", cache.get("ListJsonsByAuthor"), expiration = 10.minutes)
      Ok(Json.obj("articles" -> listJsons))
    }

  }

  def getTopArticleStatsByViews() = SecuredAction {
    implicit request => {
      val listJsons: List[JsObject] = ArticleRepository.getTopArticleStatsByViews().map {
        articleStats => ArticleStats.articleStatsWriter.writes(articleStats)
      }
      Ok(Json.obj("articles" -> listJsons))

    }
  }

  def viewArticle(articleId: Long) = UserAwareAction {
    implicit request => {
      request.identity match {
        case None => Ok(Json.obj("viewArticle" -> ViewRepository.create(0.toLong, articleId)))
        case Some(user) => Ok(Json.obj("viewArticle" -> ViewRepository.create(user.id.get, articleId)))
      }
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

  def likeUnlike(articleId: Long) = SecuredAction {
    implicit request => {
      request.identity.id match {
        case Some(id) => Ok(Json.obj("messages" -> LikeRepository.likesOrUnlikes(id, articleId)))
        case None => Ok(Json.obj("messages" -> "like.action.failure"))
      }
    }
  }

  def getDraftsByAuthor(): Action[AnyContent] = SecuredAction {
    implicit request => {
      val listJson = ArticleRepository.getDraftsByAuthor(request.identity.id.get).map {
        draft => if (draft.title == "") Article.articleWriter.writes(draft.copy(title = "untitled story"))
        else Article.articleWriter.writes(draft)
      }
      Ok(Json.obj("drafts" -> listJson))
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
package controllers

import models._
import repositories.ArticleRepository
import utils.silhouette._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.{ MessagesApi, Messages, Lang }
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import javax.inject.Inject

class Application @Inject() (val env: AuthenticationEnvironment, val messagesApi: MessagesApi) extends AuthenticationController {

  def index = UserAwareAction.async { implicit request =>
    val listArticles = ArticleRepository.getAllArticles().map(Article.shorten)
    Future.successful(Ok(views.html.index(listArticles)))
  }

  def myAccount = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.myAccount()))
  }

  def myProfile = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.myProfile()))
  }

  // REQUIRED ROLES: master
  def settings = SecuredAction(WithService("master")).async { implicit request =>
    Future.successful(Ok(views.html.settings()))
  }

  def selectLang(lang: String) = Action { implicit request =>
    Logger.logger.debug("Change user lang to : " + lang)
    request.headers.get(REFERER).map { referer =>
      Redirect(referer).withLang(Lang(lang))
    }.getOrElse {
      Redirect(routes.Application.index).withLang(Lang(lang))
    }
  }

}
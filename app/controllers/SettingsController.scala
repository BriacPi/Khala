package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.ws.WSClient
import utils.silhouette.{AuthenticationController, AuthenticationEnvironment}

/**
  * Created by corpus on 17/02/2016.
//  */
//class SettingsController @Inject()(ws: WSClient)(val env: AuthenticationEnvironment, val messagesApi: MessagesApi)
//  extends AuthenticationController with I18nSupport {
//  def upload = Action(parse.multipartFormData) { request =>
//    request.body.file("picture").map { picture =>
//      import java.io.File
//      val filename = picture.filename
//      val contentType = picture.contentType
//      picture.ref.moveTo(new File(s"/tmp/picture/$filename"))
//      Ok("File uploaded")
//    }.getOrElse {
//      Redirect(routes.Application.index).flashing(
//        "error" -> "Missing file")
//    }
//  }
//}


// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/corpus/Desktop/khala/conf/routes
// @DATE:Tue Feb 16 11:54:49 CET 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Auth_3: controllers.Auth,
  // @LINE:22
  Application_4: controllers.Application,
  // @LINE:31
  MyAssets_1: controllers.MyAssets,
  // @LINE:43
  ContentController_0: controllers.ContentController,
  // @LINE:50
  UserProfileController_2: controllers.UserProfileController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Auth_3: controllers.Auth,
    // @LINE:22
    Application_4: controllers.Application,
    // @LINE:31
    MyAssets_1: controllers.MyAssets,
    // @LINE:43
    ContentController_0: controllers.ContentController,
    // @LINE:50
    UserProfileController_2: controllers.UserProfileController
  ) = this(errorHandler, Auth_3, Application_4, MyAssets_1, ContentController_0, UserProfileController_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Auth_3, Application_4, MyAssets_1, ContentController_0, UserProfileController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signup""", """controllers.Auth.startSignUp"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signup""", """controllers.Auth.handleStartSignUp"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signup/$token<[^/]+>""", """controllers.Auth.signUp(token:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signin""", """controllers.Auth.signIn"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authenticate""", """controllers.Auth.authenticate"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signout""", """controllers.Auth.signOut"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reset-password""", """controllers.Auth.forgotPassword"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reset-password""", """controllers.Auth.handleForgotPassword"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reset-password/$token<[^/]+>""", """controllers.Auth.resetPassword(token:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reset-password/$token<[^/]+>""", """controllers.Auth.handleResetPassword(token:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """change-password""", """controllers.Auth.changePassword"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """change-password""", """controllers.Auth.handleChangePassword"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """denied""", """controllers.Auth.accessDenied"""),
    ("""GET""", this.prefix, """controllers.Application.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """settings""", """controllers.Application.settings"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """myaccount""", """controllers.Application.myAccount"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """myprofile""", """controllers.Application.myProfile"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """lang/$lang<(en|es)>""", """controllers.Application.selectLang(lang:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """public/$file<.+>""", """controllers.MyAssets.public(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """lib/$file<.+>""", """controllers.MyAssets.lib(path:String = "/public/lib", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """javascripts/$file<.+>""", """controllers.MyAssets.javascripts(path:String = "/public/javascripts", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """css/$file<.+>""", """controllers.MyAssets.css(path:String = "/public/stylesheets", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """js/$file<.+>""", """controllers.MyAssets.js(path:String = "/public/javascripts", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """img/$file<.+>""", """controllers.MyAssets.img(path:String = "/public/images", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """common/css/$file<.+>""", """controllers.MyAssets.commonCss(path:String = "/public/lib/common/stylesheets", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """common/js/$file<.+>""", """controllers.MyAssets.commonJs(path:String = "/public/lib/common/javascripts", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """common/img/$file<.+>""", """controllers.MyAssets.commonImg(path:String = "/public/lib/common/images", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """write-article""", """controllers.ContentController.writeArticle()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/save-article""", """controllers.ContentController.saveArticle()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/get-all-articles""", """controllers.ContentController.getAllArticles()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/get-articles-by-author""", """controllers.ContentController.getArticlesByAuthor()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/user""", """controllers.UserProfileController.getUser()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/user-info-minimal""", """controllers.UserProfileController.getUserInfoMinimal()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Auth_startSignUp0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_Auth_startSignUp0_invoker = createInvoker(
    Auth_3.startSignUp,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "startSignUp",
      Nil,
      "GET",
      """ Authentication pages""",
      this.prefix + """signup"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Auth_handleStartSignUp1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup")))
  )
  private[this] lazy val controllers_Auth_handleStartSignUp1_invoker = createInvoker(
    Auth_3.handleStartSignUp,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "handleStartSignUp",
      Nil,
      "POST",
      """""",
      this.prefix + """signup"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Auth_signUp2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signup/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_signUp2_invoker = createInvoker(
    Auth_3.signUp(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signUp",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """signup/$token<[^/]+>"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Auth_signIn3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signin")))
  )
  private[this] lazy val controllers_Auth_signIn3_invoker = createInvoker(
    Auth_3.signIn,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signIn",
      Nil,
      "GET",
      """""",
      this.prefix + """signin"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Auth_authenticate4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authenticate")))
  )
  private[this] lazy val controllers_Auth_authenticate4_invoker = createInvoker(
    Auth_3.authenticate,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "authenticate",
      Nil,
      "POST",
      """""",
      this.prefix + """authenticate"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Auth_signOut5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signout")))
  )
  private[this] lazy val controllers_Auth_signOut5_invoker = createInvoker(
    Auth_3.signOut,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "signOut",
      Nil,
      "GET",
      """""",
      this.prefix + """signout"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Auth_forgotPassword6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reset-password")))
  )
  private[this] lazy val controllers_Auth_forgotPassword6_invoker = createInvoker(
    Auth_3.forgotPassword,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "forgotPassword",
      Nil,
      "GET",
      """""",
      this.prefix + """reset-password"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Auth_handleForgotPassword7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reset-password")))
  )
  private[this] lazy val controllers_Auth_handleForgotPassword7_invoker = createInvoker(
    Auth_3.handleForgotPassword,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "handleForgotPassword",
      Nil,
      "POST",
      """""",
      this.prefix + """reset-password"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_Auth_resetPassword8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reset-password/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_resetPassword8_invoker = createInvoker(
    Auth_3.resetPassword(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "resetPassword",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """reset-password/$token<[^/]+>"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Auth_handleResetPassword9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reset-password/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Auth_handleResetPassword9_invoker = createInvoker(
    Auth_3.handleResetPassword(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "handleResetPassword",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """reset-password/$token<[^/]+>"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Auth_changePassword10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("change-password")))
  )
  private[this] lazy val controllers_Auth_changePassword10_invoker = createInvoker(
    Auth_3.changePassword,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "changePassword",
      Nil,
      "GET",
      """""",
      this.prefix + """change-password"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Auth_handleChangePassword11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("change-password")))
  )
  private[this] lazy val controllers_Auth_handleChangePassword11_invoker = createInvoker(
    Auth_3.handleChangePassword,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "handleChangePassword",
      Nil,
      "POST",
      """""",
      this.prefix + """change-password"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Auth_accessDenied12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("denied")))
  )
  private[this] lazy val controllers_Auth_accessDenied12_invoker = createInvoker(
    Auth_3.accessDenied,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Auth",
      "accessDenied",
      Nil,
      "GET",
      """""",
      this.prefix + """denied"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_Application_index13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index13_invoker = createInvoker(
    Application_4.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """ Home page""",
      this.prefix + """"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_Application_settings14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("settings")))
  )
  private[this] lazy val controllers_Application_settings14_invoker = createInvoker(
    Application_4.settings,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "settings",
      Nil,
      "GET",
      """""",
      this.prefix + """settings"""
    )
  )

  // @LINE:24
  private[this] lazy val controllers_Application_myAccount15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("myaccount")))
  )
  private[this] lazy val controllers_Application_myAccount15_invoker = createInvoker(
    Application_4.myAccount,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "myAccount",
      Nil,
      "GET",
      """""",
      this.prefix + """myaccount"""
    )
  )

  // @LINE:25
  private[this] lazy val controllers_Application_myProfile16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("myprofile")))
  )
  private[this] lazy val controllers_Application_myProfile16_invoker = createInvoker(
    Application_4.myProfile,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "myProfile",
      Nil,
      "GET",
      """""",
      this.prefix + """myprofile"""
    )
  )

  // @LINE:27
  private[this] lazy val controllers_Application_selectLang17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("lang/"), DynamicPart("lang", """(en|es)""",false)))
  )
  private[this] lazy val controllers_Application_selectLang17_invoker = createInvoker(
    Application_4.selectLang(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "selectLang",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """lang/$lang<(en|es)>"""
    )
  )

  // @LINE:31
  private[this] lazy val controllers_MyAssets_public18_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("public/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_public18_invoker = createInvoker(
    MyAssets_1.public(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "public",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """public/$file<.+>"""
    )
  )

  // @LINE:32
  private[this] lazy val controllers_MyAssets_lib19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("lib/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_lib19_invoker = createInvoker(
    MyAssets_1.lib(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "lib",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """lib/$file<.+>"""
    )
  )

  // @LINE:33
  private[this] lazy val controllers_MyAssets_javascripts20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("javascripts/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_javascripts20_invoker = createInvoker(
    MyAssets_1.javascripts(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "javascripts",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """javascripts/$file<.+>"""
    )
  )

  // @LINE:34
  private[this] lazy val controllers_MyAssets_css21_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("css/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_css21_invoker = createInvoker(
    MyAssets_1.css(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "css",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """css/$file<.+>"""
    )
  )

  // @LINE:35
  private[this] lazy val controllers_MyAssets_js22_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("js/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_js22_invoker = createInvoker(
    MyAssets_1.js(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "js",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """js/$file<.+>"""
    )
  )

  // @LINE:36
  private[this] lazy val controllers_MyAssets_img23_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("img/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_img23_invoker = createInvoker(
    MyAssets_1.img(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "img",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """img/$file<.+>"""
    )
  )

  // @LINE:37
  private[this] lazy val controllers_MyAssets_commonCss24_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("common/css/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_commonCss24_invoker = createInvoker(
    MyAssets_1.commonCss(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "commonCss",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """common/css/$file<.+>"""
    )
  )

  // @LINE:38
  private[this] lazy val controllers_MyAssets_commonJs25_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("common/js/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_commonJs25_invoker = createInvoker(
    MyAssets_1.commonJs(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "commonJs",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """common/js/$file<.+>"""
    )
  )

  // @LINE:39
  private[this] lazy val controllers_MyAssets_commonImg26_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("common/img/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_MyAssets_commonImg26_invoker = createInvoker(
    MyAssets_1.commonImg(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MyAssets",
      "commonImg",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """""",
      this.prefix + """common/img/$file<.+>"""
    )
  )

  // @LINE:43
  private[this] lazy val controllers_ContentController_writeArticle27_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("write-article")))
  )
  private[this] lazy val controllers_ContentController_writeArticle27_invoker = createInvoker(
    ContentController_0.writeArticle(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContentController",
      "writeArticle",
      Nil,
      "GET",
      """Content management""",
      this.prefix + """write-article"""
    )
  )

  // @LINE:44
  private[this] lazy val controllers_ContentController_saveArticle28_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/save-article")))
  )
  private[this] lazy val controllers_ContentController_saveArticle28_invoker = createInvoker(
    ContentController_0.saveArticle(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContentController",
      "saveArticle",
      Nil,
      "POST",
      """""",
      this.prefix + """api/save-article"""
    )
  )

  // @LINE:45
  private[this] lazy val controllers_ContentController_getAllArticles29_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/get-all-articles")))
  )
  private[this] lazy val controllers_ContentController_getAllArticles29_invoker = createInvoker(
    ContentController_0.getAllArticles(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContentController",
      "getAllArticles",
      Nil,
      "GET",
      """""",
      this.prefix + """api/get-all-articles"""
    )
  )

  // @LINE:46
  private[this] lazy val controllers_ContentController_getArticlesByAuthor30_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/get-articles-by-author")))
  )
  private[this] lazy val controllers_ContentController_getArticlesByAuthor30_invoker = createInvoker(
    ContentController_0.getArticlesByAuthor(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContentController",
      "getArticlesByAuthor",
      Nil,
      "GET",
      """""",
      this.prefix + """api/get-articles-by-author"""
    )
  )

  // @LINE:50
  private[this] lazy val controllers_UserProfileController_getUser31_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/user")))
  )
  private[this] lazy val controllers_UserProfileController_getUser31_invoker = createInvoker(
    UserProfileController_2.getUser(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserProfileController",
      "getUser",
      Nil,
      "GET",
      """UserProfile Management""",
      this.prefix + """api/user"""
    )
  )

  // @LINE:51
  private[this] lazy val controllers_UserProfileController_getUserInfoMinimal32_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/user-info-minimal")))
  )
  private[this] lazy val controllers_UserProfileController_getUserInfoMinimal32_invoker = createInvoker(
    UserProfileController_2.getUserInfoMinimal(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserProfileController",
      "getUserInfoMinimal",
      Nil,
      "GET",
      """""",
      this.prefix + """api/user-info-minimal"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Auth_startSignUp0_route(params) =>
      call { 
        controllers_Auth_startSignUp0_invoker.call(Auth_3.startSignUp)
      }
  
    // @LINE:7
    case controllers_Auth_handleStartSignUp1_route(params) =>
      call { 
        controllers_Auth_handleStartSignUp1_invoker.call(Auth_3.handleStartSignUp)
      }
  
    // @LINE:8
    case controllers_Auth_signUp2_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_signUp2_invoker.call(Auth_3.signUp(token))
      }
  
    // @LINE:9
    case controllers_Auth_signIn3_route(params) =>
      call { 
        controllers_Auth_signIn3_invoker.call(Auth_3.signIn)
      }
  
    // @LINE:10
    case controllers_Auth_authenticate4_route(params) =>
      call { 
        controllers_Auth_authenticate4_invoker.call(Auth_3.authenticate)
      }
  
    // @LINE:11
    case controllers_Auth_signOut5_route(params) =>
      call { 
        controllers_Auth_signOut5_invoker.call(Auth_3.signOut)
      }
  
    // @LINE:12
    case controllers_Auth_forgotPassword6_route(params) =>
      call { 
        controllers_Auth_forgotPassword6_invoker.call(Auth_3.forgotPassword)
      }
  
    // @LINE:13
    case controllers_Auth_handleForgotPassword7_route(params) =>
      call { 
        controllers_Auth_handleForgotPassword7_invoker.call(Auth_3.handleForgotPassword)
      }
  
    // @LINE:14
    case controllers_Auth_resetPassword8_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_resetPassword8_invoker.call(Auth_3.resetPassword(token))
      }
  
    // @LINE:15
    case controllers_Auth_handleResetPassword9_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_Auth_handleResetPassword9_invoker.call(Auth_3.handleResetPassword(token))
      }
  
    // @LINE:16
    case controllers_Auth_changePassword10_route(params) =>
      call { 
        controllers_Auth_changePassword10_invoker.call(Auth_3.changePassword)
      }
  
    // @LINE:17
    case controllers_Auth_handleChangePassword11_route(params) =>
      call { 
        controllers_Auth_handleChangePassword11_invoker.call(Auth_3.handleChangePassword)
      }
  
    // @LINE:18
    case controllers_Auth_accessDenied12_route(params) =>
      call { 
        controllers_Auth_accessDenied12_invoker.call(Auth_3.accessDenied)
      }
  
    // @LINE:22
    case controllers_Application_index13_route(params) =>
      call { 
        controllers_Application_index13_invoker.call(Application_4.index)
      }
  
    // @LINE:23
    case controllers_Application_settings14_route(params) =>
      call { 
        controllers_Application_settings14_invoker.call(Application_4.settings)
      }
  
    // @LINE:24
    case controllers_Application_myAccount15_route(params) =>
      call { 
        controllers_Application_myAccount15_invoker.call(Application_4.myAccount)
      }
  
    // @LINE:25
    case controllers_Application_myProfile16_route(params) =>
      call { 
        controllers_Application_myProfile16_invoker.call(Application_4.myProfile)
      }
  
    // @LINE:27
    case controllers_Application_selectLang17_route(params) =>
      call(params.fromPath[String]("lang", None)) { (lang) =>
        controllers_Application_selectLang17_invoker.call(Application_4.selectLang(lang))
      }
  
    // @LINE:31
    case controllers_MyAssets_public18_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_public18_invoker.call(MyAssets_1.public(path, file))
      }
  
    // @LINE:32
    case controllers_MyAssets_lib19_route(params) =>
      call(Param[String]("path", Right("/public/lib")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_lib19_invoker.call(MyAssets_1.lib(path, file))
      }
  
    // @LINE:33
    case controllers_MyAssets_javascripts20_route(params) =>
      call(Param[String]("path", Right("/public/javascripts")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_javascripts20_invoker.call(MyAssets_1.javascripts(path, file))
      }
  
    // @LINE:34
    case controllers_MyAssets_css21_route(params) =>
      call(Param[String]("path", Right("/public/stylesheets")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_css21_invoker.call(MyAssets_1.css(path, file))
      }
  
    // @LINE:35
    case controllers_MyAssets_js22_route(params) =>
      call(Param[String]("path", Right("/public/javascripts")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_js22_invoker.call(MyAssets_1.js(path, file))
      }
  
    // @LINE:36
    case controllers_MyAssets_img23_route(params) =>
      call(Param[String]("path", Right("/public/images")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_img23_invoker.call(MyAssets_1.img(path, file))
      }
  
    // @LINE:37
    case controllers_MyAssets_commonCss24_route(params) =>
      call(Param[String]("path", Right("/public/lib/common/stylesheets")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_commonCss24_invoker.call(MyAssets_1.commonCss(path, file))
      }
  
    // @LINE:38
    case controllers_MyAssets_commonJs25_route(params) =>
      call(Param[String]("path", Right("/public/lib/common/javascripts")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_commonJs25_invoker.call(MyAssets_1.commonJs(path, file))
      }
  
    // @LINE:39
    case controllers_MyAssets_commonImg26_route(params) =>
      call(Param[String]("path", Right("/public/lib/common/images")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_MyAssets_commonImg26_invoker.call(MyAssets_1.commonImg(path, file))
      }
  
    // @LINE:43
    case controllers_ContentController_writeArticle27_route(params) =>
      call { 
        controllers_ContentController_writeArticle27_invoker.call(ContentController_0.writeArticle())
      }
  
    // @LINE:44
    case controllers_ContentController_saveArticle28_route(params) =>
      call { 
        controllers_ContentController_saveArticle28_invoker.call(ContentController_0.saveArticle())
      }
  
    // @LINE:45
    case controllers_ContentController_getAllArticles29_route(params) =>
      call { 
        controllers_ContentController_getAllArticles29_invoker.call(ContentController_0.getAllArticles())
      }
  
    // @LINE:46
    case controllers_ContentController_getArticlesByAuthor30_route(params) =>
      call { 
        controllers_ContentController_getArticlesByAuthor30_invoker.call(ContentController_0.getArticlesByAuthor())
      }
  
    // @LINE:50
    case controllers_UserProfileController_getUser31_route(params) =>
      call { 
        controllers_UserProfileController_getUser31_invoker.call(UserProfileController_2.getUser())
      }
  
    // @LINE:51
    case controllers_UserProfileController_getUserInfoMinimal32_route(params) =>
      call { 
        controllers_UserProfileController_getUserInfoMinimal32_invoker.call(UserProfileController_2.getUserInfoMinimal())
      }
  }
}
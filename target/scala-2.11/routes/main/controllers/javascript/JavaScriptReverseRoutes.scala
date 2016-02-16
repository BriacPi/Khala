
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/corpus/Desktop/khala/conf/routes
// @DATE:Tue Feb 16 11:54:49 CET 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:43
  class ReverseContentController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:45
    def getAllArticles: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContentController.getAllArticles",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/get-all-articles"})
        }
      """
    )
  
    // @LINE:44
    def saveArticle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContentController.saveArticle",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/save-article"})
        }
      """
    )
  
    // @LINE:46
    def getArticlesByAuthor: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContentController.getArticlesByAuthor",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/get-articles-by-author"})
        }
      """
    )
  
    // @LINE:43
    def writeArticle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContentController.writeArticle",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "write-article"})
        }
      """
    )
  
  }

  // @LINE:31
  class ReverseMyAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:31
    def public: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.public",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "public/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:37
    def commonCss: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.commonCss",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "common/css/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:34
    def css: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.css",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "css/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:33
    def javascripts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.javascripts",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "javascripts/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:32
    def lib: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.lib",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lib/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:36
    def img: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.img",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "img/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:39
    def commonImg: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.commonImg",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "common/img/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:35
    def js: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.js",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "js/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
    // @LINE:38
    def commonJs: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MyAssets.commonJs",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "common/js/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseAuth(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def handleChangePassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.handleChangePassword",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "change-password"})
        }
      """
    )
  
    // @LINE:6
    def startSignUp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.startSignUp",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:7
    def handleStartSignUp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.handleStartSignUp",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "signup"})
        }
      """
    )
  
    // @LINE:13
    def handleForgotPassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.handleForgotPassword",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password"})
        }
      """
    )
  
    // @LINE:11
    def signOut: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signOut",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signout"})
        }
      """
    )
  
    // @LINE:14
    def resetPassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.resetPassword",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:8
    def signUp: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signUp",
      """
        function(token) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signup/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:12
    def forgotPassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.forgotPassword",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password"})
        }
      """
    )
  
    // @LINE:16
    def changePassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.changePassword",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "change-password"})
        }
      """
    )
  
    // @LINE:18
    def accessDenied: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.accessDenied",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "denied"})
        }
      """
    )
  
    // @LINE:15
    def handleResetPassword: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.handleResetPassword",
      """
        function(token) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token))})
        }
      """
    )
  
    // @LINE:10
    def authenticate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.authenticate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "authenticate"})
        }
      """
    )
  
    // @LINE:9
    def signIn: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Auth.signIn",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "signin"})
        }
      """
    )
  
  }

  // @LINE:50
  class ReverseUserProfileController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:50
    def getUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserProfileController.getUser",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/user"})
        }
      """
    )
  
    // @LINE:51
    def getUserInfoMinimal: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserProfileController.getUserInfoMinimal",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/user-info-minimal"})
        }
      """
    )
  
  }

  // @LINE:22
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def myAccount: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.myAccount",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "myaccount"})
        }
      """
    )
  
    // @LINE:25
    def myProfile: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.myProfile",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "myprofile"})
        }
      """
    )
  
    // @LINE:23
    def settings: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.settings",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "settings"})
        }
      """
    )
  
    // @LINE:22
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:27
    def selectLang: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Application.selectLang",
      """
        function(lang) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lang/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("lang", lang)})
        }
      """
    )
  
  }


}
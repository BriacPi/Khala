
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/corpus/Desktop/khala/conf/routes
// @DATE:Tue Feb 16 11:54:49 CET 2016

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:43
  class ReverseContentController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:45
    def getAllArticles(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/get-all-articles")
    }
  
    // @LINE:44
    def saveArticle(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/save-article")
    }
  
    // @LINE:46
    def getArticlesByAuthor(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/get-articles-by-author")
    }
  
    // @LINE:43
    def writeArticle(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "write-article")
    }
  
  }

  // @LINE:31
  class ReverseMyAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:31
    def public(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "public/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:37
    def commonCss(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/lib/common/stylesheets")))
      Call("GET", _prefix + { _defaultPrefix } + "common/css/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:34
    def css(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/stylesheets")))
      Call("GET", _prefix + { _defaultPrefix } + "css/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:33
    def javascripts(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts")))
      Call("GET", _prefix + { _defaultPrefix } + "javascripts/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:32
    def lib(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/lib")))
      Call("GET", _prefix + { _defaultPrefix } + "lib/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:36
    def img(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/images")))
      Call("GET", _prefix + { _defaultPrefix } + "img/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:39
    def commonImg(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/lib/common/images")))
      Call("GET", _prefix + { _defaultPrefix } + "common/img/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:35
    def js(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/javascripts")))
      Call("GET", _prefix + { _defaultPrefix } + "js/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
    // @LINE:38
    def commonJs(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public/lib/common/javascripts")))
      Call("GET", _prefix + { _defaultPrefix } + "common/js/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:6
  class ReverseAuth(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def handleChangePassword(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "change-password")
    }
  
    // @LINE:6
    def startSignUp(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:7
    def handleStartSignUp(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "signup")
    }
  
    // @LINE:13
    def handleForgotPassword(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "reset-password")
    }
  
    // @LINE:11
    def signOut(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signout")
    }
  
    // @LINE:14
    def resetPassword(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "reset-password/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:8
    def signUp(token:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signup/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:12
    def forgotPassword(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "reset-password")
    }
  
    // @LINE:16
    def changePassword(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "change-password")
    }
  
    // @LINE:18
    def accessDenied(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "denied")
    }
  
    // @LINE:15
    def handleResetPassword(token:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "reset-password/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:10
    def authenticate(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "authenticate")
    }
  
    // @LINE:9
    def signIn(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "signin")
    }
  
  }

  // @LINE:50
  class ReverseUserProfileController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:50
    def getUser(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/user")
    }
  
    // @LINE:51
    def getUserInfoMinimal(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/user-info-minimal")
    }
  
  }

  // @LINE:22
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def myAccount(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "myaccount")
    }
  
    // @LINE:25
    def myProfile(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "myprofile")
    }
  
    // @LINE:23
    def settings(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "settings")
    }
  
    // @LINE:22
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
    // @LINE:27
    def selectLang(lang:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "lang/" + implicitly[PathBindable[String]].unbind("lang", lang))
    }
  
  }


}
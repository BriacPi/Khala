
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object accessDenied_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class accessDenied extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Option[User],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit loggedUser: Option[User], request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.83*/("""
"""),_display_(/*2.2*/views/*2.7*/.html.templates.default("Access Denied", loggedUser)/*2.59*/ {_display_(Seq[Any](format.raw/*2.61*/("""
	"""),_display_(/*3.3*/tags/*3.7*/.alertFromRequest(request, "error")),format.raw/*3.42*/("""
	"""),format.raw/*4.2*/("""<h1 class="text-primary">"""),_display_(/*4.28*/Messages("denied.title")),format.raw/*4.52*/("""</h1>
	<h3 class="text-danger">"""),_display_(/*5.27*/Messages("denied.text")),format.raw/*5.50*/("""</h3>
""")))}))
      }
    }
  }

  def render(loggedUser:Option[User],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply()(loggedUser,request,messages)

  def f:(() => (Option[User],RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = () => (loggedUser,request,messages) => apply()(loggedUser,request,messages)

  def ref: this.type = this

}


}

/**/
object accessDenied extends accessDenied_Scope0.accessDenied
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/accessDenied.scala.html
                  HASH: 97be4ad5a7640b6bbe07be5bb7da73a03f0e2ce7
                  MATRIX: 575->1|751->82|779->85|791->90|851->142|890->144|919->148|930->152|985->187|1014->190|1066->216|1110->240|1169->273|1212->296
                  LINES: 20->1|25->1|26->2|26->2|26->2|26->2|27->3|27->3|27->3|28->4|28->4|28->4|29->5|29->5
                  -- GENERATED --
              */
          
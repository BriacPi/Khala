
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signedUp_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class signedUp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[User,RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(user: User)(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.67*/("""
"""),_display_(/*2.2*/default("Signed Up", loggedUser = Some(user))/*2.47*/ {_display_(Seq[Any](format.raw/*2.49*/("""
	"""),format.raw/*3.2*/("""<p>"""),_display_(/*3.6*/Messages("signup.thanks", user.fullName)),format.raw/*3.46*/("""</p>
	<p>"""),_display_(/*4.6*/Messages("signup.ready")),format.raw/*4.30*/("""</p>
	<a href=""""),_display_(/*5.12*/controllers/*5.23*/.routes.Application.index),format.raw/*5.48*/("""">"""),_display_(/*5.51*/Messages("go.index")),format.raw/*5.71*/("""</a>
""")))}))
      }
    }
  }

  def render(user:User,request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(user)(request,messages)

  def f:((User) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (user) => (request,messages) => apply(user)(request,messages)

  def ref: this.type = this

}


}

/**/
object signedUp extends signedUp_Scope0.signedUp
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/signedUp.scala.html
                  HASH: 08e82fd79d741c9514cdd542aba50ca3208b5d63
                  MATRIX: 559->1|719->66|747->69|800->114|839->116|868->119|897->123|957->163|993->174|1037->198|1080->215|1099->226|1144->251|1173->254|1213->274
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3|27->3|28->4|28->4|29->5|29->5|29->5|29->5|29->5
                  -- GENERATED --
              */
          
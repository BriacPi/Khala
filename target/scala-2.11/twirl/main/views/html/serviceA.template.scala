
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object serviceA_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class serviceA extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[User,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit user: User, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import controllers.routes

Seq[Any](format.raw/*1.45*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/views/*5.7*/.html.templates.logged(title = "Service A Area", tab = "serviceA")/*5.73*/ {_display_(Seq[Any](format.raw/*5.75*/("""
	
	"""),format.raw/*7.2*/("""<h1 class="text-primary">Service A Area</h1>
	
	<h3>This is the Service A Area. Only users with <strong>'serviceA'</strong> service can be here.</h3>

""")))}),format.raw/*11.2*/("""
"""))
      }
    }
  }

  def render(user:User,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply()(user,messages)

  def f:(() => (User,Messages) => play.twirl.api.HtmlFormat.Appendable) = () => (user,messages) => apply()(user,messages)

  def ref: this.type = this

}


}

/**/
object serviceA extends serviceA_Scope0.serviceA
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/serviceA.scala.html
                  HASH: c62c794d8957c39054d4665d73a54e864d97dec8
                  MATRIX: 540->1|703->44|733->76|761->79|773->84|847->150|886->152|918->158|1104->314
                  LINES: 20->1|25->1|27->4|28->5|28->5|28->5|28->5|30->7|34->11
                  -- GENERATED --
              */
          
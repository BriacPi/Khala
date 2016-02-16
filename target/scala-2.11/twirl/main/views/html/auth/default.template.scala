
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object default_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class default extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Option[User],Html,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, loggedUser: Option[User] = None)(content: Html)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.94*/("""
"""),_display_(/*2.2*/views/*2.7*/.html.templates.default(title, loggedUser, withMainTabs = false)/*2.71*/ {_display_(Seq[Any](format.raw/*2.73*/("""
	"""),format.raw/*3.2*/("""<div class="row">
		<div class="col-md-6 col-md-offset-3">
			"""),_display_(/*5.5*/content),format.raw/*5.12*/("""
		"""),format.raw/*6.3*/("""</div>
	</div>
""")))}))
      }
    }
  }

  def render(title:String,loggedUser:Option[User],content:Html,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(title,loggedUser)(content)(messages)

  def f:((String,Option[User]) => (Html) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (title,loggedUser) => (content) => (messages) => apply(title,loggedUser)(content)(messages)

  def ref: this.type = this

}


}

/**/
object default extends default_Scope0.default
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/default.scala.html
                  HASH: 49512fefd672854f3e8eb88f06cc1618e4c3e180
                  MATRIX: 563->1|750->93|778->96|790->101|862->165|901->167|930->170|1020->235|1047->242|1077->246
                  LINES: 20->1|25->1|26->2|26->2|26->2|26->2|27->3|29->5|29->5|30->6
                  -- GENERATED --
              */
          
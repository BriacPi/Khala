
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Option[User],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit loggedUser: Option[User], messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import views.html.tags.auth._
import controllers.routes
def /*6.2*/servicesString/*6.16*/(user: User) = {{ user.services match {
	case Nil => "none"
	case head :: Nil => head
	case init :+ last => init.mkString(", ") + " " + Messages("and") + " " + last
}}};
Seq[Any](format.raw/*1.59*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*10.3*/("""

"""),_display_(/*12.2*/views/*12.7*/.html.templates.default(title = "Home", loggedUser, tab = "index")/*12.73*/ {_display_(Seq[Any](format.raw/*12.75*/("""
		


	""")))}),format.raw/*16.3*/("""
	"""))
      }
    }
  }

  def render(loggedUser:Option[User],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply()(loggedUser,messages)

  def f:(() => (Option[User],Messages) => play.twirl.api.HtmlFormat.Appendable) = () => (loggedUser,messages) => apply()(loggedUser,messages)

  def ref: this.type = this

}


}

/**/
object index extends index_Scope0.index
              /*
                  -- GENERATED --
                  DATE: Sun Feb 14 15:28:38 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/index.scala.html
                  HASH: c07369bbd711a29b43963e22d3b863f6bc4cd353
                  MATRIX: 542->1|732->125|754->139|955->58|985->122|1014->310|1045->315|1058->320|1133->386|1173->388|1215->400
                  LINES: 20->1|25->6|25->6|30->1|32->5|33->10|35->12|35->12|35->12|35->12|39->16
                  -- GENERATED --
              */
          

package views.html.tags.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object withService_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class withService extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Array[String],Html,User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(anyOf: String*)(body: Html)(implicit loggedUser: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.57*/("""
"""),_display_(/*2.2*/if(utils.silhouette.WithService.isAuthorized(loggedUser, anyOf:_*))/*2.69*/ {_display_(Seq[Any](format.raw/*2.71*/("""
	"""),_display_(/*3.3*/body),format.raw/*3.7*/("""
""")))}))
      }
    }
  }

  def render(anyOf:Array[String],body:Html,loggedUser:User): play.twirl.api.HtmlFormat.Appendable = apply(anyOf:_*)(body)(loggedUser)

  def f:((Array[String]) => (Html) => (User) => play.twirl.api.HtmlFormat.Appendable) = (anyOf) => (body) => (loggedUser) => apply(anyOf:_*)(body)(loggedUser)

  def ref: this.type = this

}


}

/**/
object withService extends withService_Scope0.withService
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/tags/auth/withService.scala.html
                  HASH: 72066e3618c62c56048edf35ad392848447ddc6d
                  MATRIX: 566->1|716->56|744->59|819->126|858->128|887->132|910->136
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3
                  -- GENERATED --
              */
          
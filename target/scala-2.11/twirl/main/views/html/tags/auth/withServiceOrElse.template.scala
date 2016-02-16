
package views.html.tags.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object withServiceOrElse_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class withServiceOrElse extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Array[String],Html,Html,User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(anyOf: String*)(body: Html)(orElseBody: Html)(implicit loggedUser: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.75*/("""
"""),_display_(/*2.2*/if(utils.silhouette.WithService.isAuthorized(loggedUser, anyOf:_*))/*2.69*/ {_display_(Seq[Any](format.raw/*2.71*/("""
	"""),_display_(/*3.3*/body),format.raw/*3.7*/("""
""")))}/*4.3*/else/*4.8*/{_display_(Seq[Any](format.raw/*4.9*/("""
	"""),_display_(/*5.3*/orElseBody),format.raw/*5.13*/("""
""")))}))
      }
    }
  }

  def render(anyOf:Array[String],body:Html,orElseBody:Html,loggedUser:User): play.twirl.api.HtmlFormat.Appendable = apply(anyOf:_*)(body)(orElseBody)(loggedUser)

  def f:((Array[String]) => (Html) => (Html) => (User) => play.twirl.api.HtmlFormat.Appendable) = (anyOf) => (body) => (orElseBody) => (loggedUser) => apply(anyOf:_*)(body)(orElseBody)(loggedUser)

  def ref: this.type = this

}


}

/**/
object withServiceOrElse extends withServiceOrElse_Scope0.withServiceOrElse
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/tags/auth/withServiceOrElse.scala.html
                  HASH: 32f447a0ffeb5888f876ea1b256b2313e97d53a0
                  MATRIX: 583->1|751->74|779->77|854->144|893->146|922->150|945->154|965->158|976->163|1013->164|1042->168|1072->178
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3|28->4|28->4|28->4|29->5|29->5
                  -- GENERATED --
              */
          
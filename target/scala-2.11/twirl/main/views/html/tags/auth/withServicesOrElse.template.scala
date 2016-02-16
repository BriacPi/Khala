
package views.html.tags.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object withServicesOrElse_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class withServicesOrElse extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[Array[String],Html,Html,User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(allOf: String*)(body: Html)(orElseBody: Html)(implicit loggedUser: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.75*/("""
"""),_display_(/*2.2*/if(utils.silhouette.WithServices.isAuthorized(loggedUser, allOf:_*))/*2.70*/ {_display_(Seq[Any](format.raw/*2.72*/("""
	"""),_display_(/*3.3*/body),format.raw/*3.7*/("""
""")))}/*4.3*/else/*4.8*/{_display_(Seq[Any](format.raw/*4.9*/("""
	"""),_display_(/*5.3*/orElseBody),format.raw/*5.13*/("""
""")))}))
      }
    }
  }

  def render(allOf:Array[String],body:Html,orElseBody:Html,loggedUser:User): play.twirl.api.HtmlFormat.Appendable = apply(allOf:_*)(body)(orElseBody)(loggedUser)

  def f:((Array[String]) => (Html) => (Html) => (User) => play.twirl.api.HtmlFormat.Appendable) = (allOf) => (body) => (orElseBody) => (loggedUser) => apply(allOf:_*)(body)(orElseBody)(loggedUser)

  def ref: this.type = this

}


}

/**/
object withServicesOrElse extends withServicesOrElse_Scope0.withServicesOrElse
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/tags/auth/withServicesOrElse.scala.html
                  HASH: 430d3eaf6734a0bb374b40568e9daf88b0289bc8
                  MATRIX: 585->1|753->74|781->77|857->145|896->147|925->151|948->155|968->159|979->164|1016->165|1045->169|1075->179
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3|28->4|28->4|28->4|29->5|29->5
                  -- GENERATED --
              */
          
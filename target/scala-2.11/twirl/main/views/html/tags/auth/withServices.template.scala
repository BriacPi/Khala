
package views.html.tags.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object withServices_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class withServices extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Array[String],Html,User,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(allOf: String*)(body: Html)(implicit loggedUser: User):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.57*/("""
"""),_display_(/*2.2*/if(utils.silhouette.WithServices.isAuthorized(loggedUser, allOf:_*))/*2.70*/ {_display_(Seq[Any](format.raw/*2.72*/("""
	"""),_display_(/*3.3*/body),format.raw/*3.7*/("""
""")))}))
      }
    }
  }

  def render(allOf:Array[String],body:Html,loggedUser:User): play.twirl.api.HtmlFormat.Appendable = apply(allOf:_*)(body)(loggedUser)

  def f:((Array[String]) => (Html) => (User) => play.twirl.api.HtmlFormat.Appendable) = (allOf) => (body) => (loggedUser) => apply(allOf:_*)(body)(loggedUser)

  def ref: this.type = this

}


}

/**/
object withServices extends withServices_Scope0.withServices
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/tags/auth/withServices.scala.html
                  HASH: cd58108b02e42f867cefe83483c0179d42dea003
                  MATRIX: 568->1|718->56|746->59|822->127|861->129|890->133|913->137
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3
                  -- GENERATED --
              */
          
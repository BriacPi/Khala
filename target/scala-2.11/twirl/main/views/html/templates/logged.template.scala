
package views.html.templates

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object logged_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class logged extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template8[String,String,Html,Html,Html,Html,User,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, tab: String = "", styles: Html = Html(""), scripts: Html = Html(""), modals: Html = Html(""))(content: Html)(implicit loggedUser: User, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.173*/("""
"""),_display_(/*2.2*/default(title, loggedUser = Some(loggedUser), withMainTabs = true, tab, styles, scripts, modals)/*2.98*/(content)/*2.107*/(messages)))
      }
    }
  }

  def render(title:String,tab:String,styles:Html,scripts:Html,modals:Html,content:Html,loggedUser:User,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(title,tab,styles,scripts,modals)(content)(loggedUser,messages)

  def f:((String,String,Html,Html,Html) => (Html) => (User,Messages) => play.twirl.api.HtmlFormat.Appendable) = (title,tab,styles,scripts,modals) => (content) => (loggedUser,messages) => apply(title,tab,styles,scripts,modals)(content)(loggedUser,messages)

  def ref: this.type = this

}


}

/**/
object logged extends logged_Scope0.logged
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/templates/logged.scala.html
                  HASH: 13cc1fbc5819b0702b7527aa2d96f92e96f1c0f5
                  MATRIX: 580->1|847->172|875->175|979->271|997->280
                  LINES: 20->1|25->1|26->2|26->2|26->2
                  -- GENERATED --
              */
          
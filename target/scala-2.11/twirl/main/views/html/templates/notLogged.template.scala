
package views.html.templates

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object notLogged_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class notLogged extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template7[String,String,Html,Html,Html,Html,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, tab: String = "", styles: Html = Html(""), scripts: Html = Html(""), modals: Html = Html(""))(content: Html)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.155*/("""
"""),_display_(/*2.2*/default(title, loggedUser = None, withMainTabs = true, tab, styles, scripts, modals)/*2.86*/(content)/*2.95*/(messages)))
      }
    }
  }

  def render(title:String,tab:String,styles:Html,scripts:Html,modals:Html,content:Html,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(title,tab,styles,scripts,modals)(content)(messages)

  def f:((String,String,Html,Html,Html) => (Html) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (title,tab,styles,scripts,modals) => (content) => (messages) => apply(title,tab,styles,scripts,modals)(content)(messages)

  def ref: this.type = this

}


}

/**/
object notLogged extends notLogged_Scope0.notLogged
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/templates/notLogged.scala.html
                  HASH: 23297f305320b34c601a9db18b9a8f26dd13fe85
                  MATRIX: 581->1|830->154|858->157|950->241|967->250
                  LINES: 20->1|25->1|26->2|26->2|26->2
                  -- GENERATED --
              */
          
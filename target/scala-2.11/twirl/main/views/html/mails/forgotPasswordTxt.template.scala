
package views.html.mails

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object forgotPasswordTxt_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class forgotPasswordTxt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String, link: String)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.59*/("""
"""),_display_(/*2.2*/Messages("mail.forgotpwd.prelink")),format.raw/*2.36*/("""

"""),_display_(/*4.2*/link),format.raw/*4.6*/("""

"""),_display_(/*6.2*/Messages("mail.forgotpwd.postlink")),format.raw/*6.37*/("""

"""),_display_(/*8.2*/Messages("mail.sign")))
      }
    }
  }

  def render(name:String,link:String,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(name,link)(messages)

  def f:((String,String) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (name,link) => (messages) => apply(name,link)(messages)

  def ref: this.type = this

}


}

/**/
object forgotPasswordTxt extends forgotPasswordTxt_Scope0.forgotPasswordTxt
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/mails/forgotPasswordTxt.scala.html
                  HASH: d3c7e7a9a2c1b1e6980b578f2742f77b73ac1987
                  MATRIX: 573->1|725->58|753->61|807->95|837->100|860->104|890->109|945->144|975->149
                  LINES: 20->1|25->1|26->2|26->2|28->4|28->4|30->6|30->6|32->8
                  -- GENERATED --
              */
          
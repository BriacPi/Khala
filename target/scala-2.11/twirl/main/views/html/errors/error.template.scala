
package views.html.errors

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object error_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class error extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[RequestHeader,play.api.UsefulException,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: RequestHeader, error: play.api.UsefulException)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.88*/("""
"""),_display_(/*2.2*/views/*2.7*/.html.templates.main("ADMIN Oops, an error occurred")/*2.60*/ {_display_(Seq[Any](format.raw/*2.62*/("""
	"""),format.raw/*3.2*/("""<h1>ADMIN """),_display_(/*3.13*/Messages("error.unknown.title")),format.raw/*3.44*/("""</h1>
	<p>"""),_display_(/*4.6*/Messages("error.unknown.text", error.id)),format.raw/*4.46*/("""</p>
""")))}))
      }
    }
  }

  def render(request:RequestHeader,error:play.api.UsefulException,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(request,error)(messages)

  def f:((RequestHeader,play.api.UsefulException) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (request,error) => (messages) => apply(request,error)(messages)

  def ref: this.type = this

}


}

/**/
object error extends error_Scope0.error
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/errors/error.scala.html
                  HASH: 9af87c764c49ef3b78884a4c5141da3fbcb6bfac
                  MATRIX: 575->1|756->87|784->90|796->95|857->148|896->150|925->153|962->164|1013->195|1050->207|1110->247
                  LINES: 20->1|25->1|26->2|26->2|26->2|26->2|27->3|27->3|27->3|28->4|28->4
                  -- GENERATED --
              */
          
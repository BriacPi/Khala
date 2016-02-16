
package views.html.errors

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object notFound_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class notFound extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(request: RequestHeader)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.55*/("""
"""),_display_(/*2.2*/views/*2.7*/.html.templates.main("ADMIN Handler Not Found")/*2.54*/ {_display_(Seq[Any](format.raw/*2.56*/("""
  """),format.raw/*3.3*/("""<h1>ADMIN """),_display_(/*3.14*/Messages("error.notfound.title")),format.raw/*3.46*/("""</h1>
	<p>"""),_display_(/*4.6*/Messages("error.notfound.text", request.method + " " + request.uri)),format.raw/*4.73*/("""</p>
""")))}))
      }
    }
  }

  def render(request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(request)(messages)

  def f:((RequestHeader) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (request) => (messages) => apply(request)(messages)

  def ref: this.type = this

}


}

/**/
object notFound extends notFound_Scope0.notFound
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/errors/notFound.scala.html
                  HASH: e7747557f52c635054e2c5e251c4b76fe3228e98
                  MATRIX: 556->1|704->54|732->57|744->62|799->109|838->111|868->115|905->126|957->158|994->170|1081->237
                  LINES: 20->1|25->1|26->2|26->2|26->2|26->2|27->3|27->3|27->3|28->4|28->4
                  -- GENERATED --
              */
          
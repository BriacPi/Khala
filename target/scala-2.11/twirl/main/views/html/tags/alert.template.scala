
package views.html.tags

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object alert_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class alert extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(msg: String, key: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*2.2*/alertType/*2.11*/ = {{ "alert-" + (if (key == "error") "danger" else key) }};
Seq[Any](format.raw/*1.28*/("""
"""),format.raw/*2.69*/("""
"""),format.raw/*3.1*/("""<div class="alert """),_display_(/*3.20*/alertType),format.raw/*3.29*/(""" """),format.raw/*3.30*/("""alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	"""),_display_(/*5.3*/msg),format.raw/*5.6*/("""
"""),format.raw/*6.1*/("""</div>"""))
      }
    }
  }

  def render(msg:String,key:String): play.twirl.api.HtmlFormat.Appendable = apply(msg,key)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (msg,key) => apply(msg,key)

  def ref: this.type = this

}


}

/**/
object alert extends alert_Scope0.alert
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/tags/alert.scala.html
                  HASH: 18d9589fbf0bec83dc0bc295d029b77d097ec6a8
                  MATRIX: 539->1|643->30|660->39|748->27|777->97|805->99|850->118|879->127|907->128|1110->306|1132->309|1160->311
                  LINES: 20->1|24->2|24->2|25->1|26->2|27->3|27->3|27->3|27->3|29->5|29->5|30->6
                  -- GENERATED --
              */
          
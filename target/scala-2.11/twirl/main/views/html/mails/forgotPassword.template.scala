
package views.html.mails

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object forgotPassword_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class forgotPassword extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[String,String,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String, link: String)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.59*/("""
"""),format.raw/*2.1*/("""<html>
<head><meta charset="utf-8"></head>
<body>
	<p>"""),_display_(/*5.6*/Messages("mail.forgotpwd.prelink")),format.raw/*5.40*/("""</p>
	<a href=""""),_display_(/*6.12*/link),format.raw/*6.16*/("""">"""),_display_(/*6.19*/link),format.raw/*6.23*/("""</a>
	<p>"""),_display_(/*7.6*/Messages("mail.forgotpwd.postlink")),format.raw/*7.41*/("""</p>
	<p>"""),_display_(/*8.6*/Messages("mail.sign")),format.raw/*8.27*/("""</p>
</body>
</html>"""))
      }
    }
  }

  def render(name:String,link:String,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(name,link)(messages)

  def f:((String,String) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (name,link) => (messages) => apply(name,link)(messages)

  def ref: this.type = this

}


}

/**/
object forgotPassword extends forgotPassword_Scope0.forgotPassword
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/mails/forgotPassword.scala.html
                  HASH: 54cdff8668787121788d2ca319e2b1d107633364
                  MATRIX: 567->1|719->58|747->60|830->118|884->152|927->169|951->173|980->176|1004->180|1040->191|1095->226|1131->237|1172->258
                  LINES: 20->1|25->1|26->2|29->5|29->5|30->6|30->6|30->6|30->6|31->7|31->7|32->8|32->8
                  -- GENERATED --
              */
          
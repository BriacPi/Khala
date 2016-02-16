
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object resetPassword_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class resetPassword extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template4[String,Form[scala.Tuple2[String, String]],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(tokenId: String, passwordsForm: Form[(String, String)])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import b3.vertical.fieldConstructor

Seq[Any](format.raw/*1.111*/("""
"""),_display_(/*3.2*/default("Reset Password")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""
	"""),_display_(/*4.3*/tags/*4.7*/.alertFromRequest(request, "error")),format.raw/*4.42*/("""
	"""),_display_(/*5.3*/passwordsForm/*5.16*/.globalError.map/*5.32*/ { error =>_display_(Seq[Any](format.raw/*5.43*/("""
		"""),_display_(/*6.4*/tags/*6.8*/.alert(error.message, "error")),format.raw/*6.38*/("""
	""")))}),format.raw/*7.3*/("""
	"""),_display_(/*8.3*/b3/*8.5*/.form(controllers.routes.Auth.handleResetPassword(tokenId))/*8.64*/ {_display_(Seq[Any](format.raw/*8.66*/("""
		"""),format.raw/*9.3*/("""<fieldset>
			<legend>"""),_display_(/*10.13*/Messages("forgot.reset.title")),format.raw/*10.43*/("""</legend>
			"""),_display_(/*11.5*/b3/*11.7*/.password( passwordsForm("password1"), '_hiddenLabel -> Messages("field.password"), 'placeholder -> Messages("field.password"), 'autofocus -> true )),format.raw/*11.155*/("""
			"""),_display_(/*12.5*/b3/*12.7*/.password( passwordsForm("password2"), '_hiddenLabel -> Messages("field.password.repeat"), 'placeholder -> Messages("field.password.repeat") )),format.raw/*12.149*/("""
			"""),_display_(/*13.5*/b3/*13.7*/.submit('class -> "btn btn-primary btn-block")/*13.53*/{_display_(Seq[Any](format.raw/*13.54*/(""" """),_display_(/*13.56*/Messages("reset")),format.raw/*13.73*/(""" """)))}),format.raw/*13.75*/("""
		"""),format.raw/*14.3*/("""</fieldset>
	""")))}),format.raw/*15.3*/("""
""")))}))
      }
    }
  }

  def render(tokenId:String,passwordsForm:Form[scala.Tuple2[String, String]],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(tokenId,passwordsForm)(request,messages)

  def f:((String,Form[scala.Tuple2[String, String]]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (tokenId,passwordsForm) => (request,messages) => apply(tokenId,passwordsForm)(request,messages)

  def ref: this.type = this

}


}

/**/
object resetPassword extends resetPassword_Scope0.resetPassword
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/resetPassword.scala.html
                  HASH: 4e4e08b12614e5c4cd9ba566ac1b76f26aa56801
                  MATRIX: 606->1|846->110|874->151|907->176|946->178|975->182|986->186|1041->221|1070->225|1091->238|1115->254|1163->265|1193->270|1204->274|1254->304|1287->308|1316->312|1325->314|1392->373|1431->375|1461->379|1512->403|1563->433|1604->448|1614->450|1784->598|1816->604|1826->606|1990->748|2022->754|2032->756|2087->802|2126->803|2155->805|2193->822|2226->824|2257->828|2302->843
                  LINES: 20->1|25->1|26->3|26->3|26->3|27->4|27->4|27->4|28->5|28->5|28->5|28->5|29->6|29->6|29->6|30->7|31->8|31->8|31->8|31->8|32->9|33->10|33->10|34->11|34->11|34->11|35->12|35->12|35->12|36->13|36->13|36->13|36->13|36->13|36->13|36->13|37->14|38->15
                  -- GENERATED --
              */
          
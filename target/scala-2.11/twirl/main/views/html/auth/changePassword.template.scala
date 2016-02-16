
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object changePassword_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class changePassword extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[scala.Tuple3[String, String, String]],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(passwordsForm: Form[(String, String, String)])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import b3.vertical.fieldConstructor
import controllers.routes

Seq[Any](format.raw/*1.102*/("""
"""),_display_(/*4.2*/default("Change Password")/*4.28*/ {_display_(Seq[Any](format.raw/*4.30*/("""
	"""),_display_(/*5.3*/tags/*5.7*/.alertFromRequest(request, "error")),format.raw/*5.42*/("""
	"""),_display_(/*6.3*/passwordsForm/*6.16*/.globalError.map/*6.32*/ { error =>_display_(Seq[Any](format.raw/*6.43*/("""
		"""),_display_(/*7.4*/tags/*7.8*/.alert(error.message, "error")),format.raw/*7.38*/("""
	""")))}),format.raw/*8.3*/("""
	"""),_display_(/*9.3*/b3/*9.5*/.form(routes.Auth.handleChangePassword)/*9.44*/ {_display_(Seq[Any](format.raw/*9.46*/("""
		"""),format.raw/*10.3*/("""<fieldset>
			<legend>"""),_display_(/*11.13*/Messages("changepass.title")),format.raw/*11.41*/("""</legend>
			"""),_display_(/*12.5*/b3/*12.7*/.password( passwordsForm("current"), '_hiddenLabel -> Messages("changepass.field.current"), 'placeholder -> Messages("changepass.field.current"), 'autofocus -> true )),format.raw/*12.173*/("""
			"""),_display_(/*13.5*/b3/*13.7*/.password( passwordsForm("password1"), '_hiddenLabel -> Messages("changepass.field.new"), 'placeholder -> Messages("changepass.field.new") )),format.raw/*13.147*/("""
			"""),_display_(/*14.5*/b3/*14.7*/.password( passwordsForm("password2"), '_hiddenLabel -> Messages("changepass.field.repeat"), 'placeholder -> Messages("changepass.field.repeat") )),format.raw/*14.153*/("""
			"""),_display_(/*15.5*/b3/*15.7*/.submit('class -> "btn btn-primary btn-block")/*15.53*/{_display_(Seq[Any](format.raw/*15.54*/(""" """),_display_(/*15.56*/Messages("reset")),format.raw/*15.73*/(""" """)))}),format.raw/*15.75*/("""
			"""),format.raw/*16.4*/("""<a class="btn btn-default btn-block" href=""""),_display_(/*16.48*/routes/*16.54*/.Application.myAccount),format.raw/*16.76*/("""">"""),_display_(/*16.79*/Messages("back")),format.raw/*16.95*/("""</a>
		</fieldset>
	""")))}),format.raw/*18.3*/("""
""")))}))
      }
    }
  }

  def render(passwordsForm:Form[scala.Tuple3[String, String, String]],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(passwordsForm)(request,messages)

  def f:((Form[scala.Tuple3[String, String, String]]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (passwordsForm) => (request,messages) => apply(passwordsForm)(request,messages)

  def ref: this.type = this

}


}

/**/
object changePassword extends changePassword_Scope0.changePassword
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/changePassword.scala.html
                  HASH: bf2bc83c904398209608aa7ca31b0833d3f53363
                  MATRIX: 609->1|866->101|894->170|928->196|967->198|996->202|1007->206|1062->241|1091->245|1112->258|1136->274|1184->285|1214->290|1225->294|1275->324|1308->328|1337->332|1346->334|1393->373|1432->375|1463->379|1514->403|1563->431|1604->446|1614->448|1802->614|1834->620|1844->622|2006->762|2038->768|2048->770|2216->916|2248->922|2258->924|2313->970|2352->971|2381->973|2419->990|2452->992|2484->997|2555->1041|2570->1047|2613->1069|2643->1072|2680->1088|2733->1111
                  LINES: 20->1|26->1|27->4|27->4|27->4|28->5|28->5|28->5|29->6|29->6|29->6|29->6|30->7|30->7|30->7|31->8|32->9|32->9|32->9|32->9|33->10|34->11|34->11|35->12|35->12|35->12|36->13|36->13|36->13|37->14|37->14|37->14|38->15|38->15|38->15|38->15|38->15|38->15|38->15|39->16|39->16|39->16|39->16|39->16|39->16|41->18
                  -- GENERATED --
              */
          
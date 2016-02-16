
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signUp_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class signUp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[User],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(signUpForm: Form[User])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import b3.vertical.fieldConstructor
import controllers.routes

Seq[Any](format.raw/*1.79*/("""
"""),_display_(/*4.2*/default("Sign Up")/*4.20*/ {_display_(Seq[Any](format.raw/*4.22*/("""
	"""),_display_(/*5.3*/tags/*5.7*/.alertFromRequest(request, "error")),format.raw/*5.42*/("""
	"""),_display_(/*6.3*/b3/*6.5*/.form(routes.Auth.handleStartSignUp)/*6.41*/ {_display_(Seq[Any](format.raw/*6.43*/("""
		"""),format.raw/*7.3*/("""<fieldset>
			<legend>"""),_display_(/*8.13*/Messages("signup.title")),format.raw/*8.37*/("""</legend>
			"""),_display_(/*9.5*/b3/*9.7*/.text(signUpForm("firstName"), '_label -> Messages("field.firstname"), 'placeholder -> "John", 'autofocus -> true )),format.raw/*9.122*/("""
			"""),_display_(/*10.5*/b3/*10.7*/.text(signUpForm("lastName"), '_label -> Messages("field.lastname"), 'placeholder -> "Doe" )),format.raw/*10.99*/("""
			"""),_display_(/*11.5*/b3/*11.7*/.text(signUpForm("email"), '_label -> Messages("field.email"), 'placeholder -> "your@email.com" )),format.raw/*11.104*/("""
			"""),_display_(/*12.5*/b3/*12.7*/.password(signUpForm("password"), '_label -> Messages("field.password"), 'placeholder -> Messages("field.password") )),format.raw/*12.124*/("""
			"""),_display_(/*13.5*/b3/*13.7*/.submit('class -> "btn btn-primary btn-block")/*13.53*/{_display_(Seq[Any](format.raw/*13.54*/(""" """),_display_(/*13.56*/Messages("signup")),format.raw/*13.74*/(""" """)))}),format.raw/*13.76*/("""
			"""),format.raw/*14.4*/("""<div class="post-form-opts">
				<p>"""),_display_(/*15.9*/Messages("signup.signin.question")),format.raw/*15.43*/(""" """),format.raw/*15.44*/("""<a href=""""),_display_(/*15.54*/routes/*15.60*/.Auth.signIn),format.raw/*15.72*/("""">"""),_display_(/*15.75*/Messages("signup.signin")),format.raw/*15.100*/("""</a></p>
			</div>
		</fieldset>
	""")))}),format.raw/*18.3*/("""
""")))}))
      }
    }
  }

  def render(signUpForm:Form[User],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(signUpForm)(request,messages)

  def f:((Form[User]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (signUpForm) => (request,messages) => apply(signUpForm)(request,messages)

  def ref: this.type = this

}


}

/**/
object signUp extends signUp_Scope0.signUp
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 16:52:01 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/signUp.scala.html
                  HASH: b526e3a9afcea536d5c82a10feaf577687d9fee5
                  MATRIX: 561->1|794->78|822->147|848->165|887->167|916->171|927->175|982->210|1011->214|1020->216|1064->252|1103->254|1133->258|1183->282|1227->306|1267->321|1276->323|1412->438|1444->444|1454->446|1567->538|1599->544|1609->546|1728->643|1760->649|1770->651|1909->768|1941->774|1951->776|2006->822|2045->823|2074->825|2113->843|2146->845|2178->850|2242->888|2297->922|2326->923|2363->933|2378->939|2411->951|2441->954|2488->979|2556->1017
                  LINES: 20->1|26->1|27->4|27->4|27->4|28->5|28->5|28->5|29->6|29->6|29->6|29->6|30->7|31->8|31->8|32->9|32->9|32->9|33->10|33->10|33->10|34->11|34->11|34->11|35->12|35->12|35->12|36->13|36->13|36->13|36->13|36->13|36->13|36->13|37->14|38->15|38->15|38->15|38->15|38->15|38->15|38->15|38->15|41->18
                  -- GENERATED --
              */
          
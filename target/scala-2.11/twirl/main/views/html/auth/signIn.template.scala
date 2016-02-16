
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signIn_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class signIn extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[Form[scala.Tuple3[String, String, Boolean]],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(signInForm: Form[(String, String, Boolean)])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import b3.vertical.fieldConstructor
import controllers.routes

Seq[Any](format.raw/*1.100*/("""
"""),_display_(/*4.2*/default("Sign In")/*4.20*/ {_display_(Seq[Any](format.raw/*4.22*/("""
	"""),_display_(/*5.3*/tags/*5.7*/.alertFromRequest(request, "error")),format.raw/*5.42*/("""
	"""),_display_(/*6.3*/b3/*6.5*/.form(routes.Auth.authenticate)/*6.36*/ {_display_(Seq[Any](format.raw/*6.38*/("""
		"""),format.raw/*7.3*/("""<fieldset>
			<legend>"""),_display_(/*8.13*/Messages("signin.title")),format.raw/*8.37*/("""</legend>
			"""),_display_(/*9.5*/b3/*9.7*/.text( signInForm("identifier"), '_hiddenLabel -> Messages("field.email"), 'placeholder -> "your@email.com", 'autofocus -> true )),format.raw/*9.136*/("""
			"""),_display_(/*10.5*/b3/*10.7*/.password( signInForm("password"), '_hiddenLabel -> Messages("field.password"), 'placeholder -> Messages("field.password") )),format.raw/*10.131*/("""
			"""),_display_(/*11.5*/b3/*11.7*/.checkbox( signInForm("rememberMe"), '_text -> Messages("signin.rememberme"), 'checked -> true )),format.raw/*11.103*/("""
			"""),_display_(/*12.5*/b3/*12.7*/.submit('class -> "btn btn-primary btn-block")/*12.53*/{_display_(Seq[Any](format.raw/*12.54*/(""" """),_display_(/*12.56*/Messages("signin")),format.raw/*12.74*/(""" """)))}),format.raw/*12.76*/("""
			"""),format.raw/*13.4*/("""<div class="post-form-opts">
				<p>"""),_display_(/*14.9*/Messages("signin.forgot.question")),format.raw/*14.43*/(""" """),format.raw/*14.44*/("""<a href=""""),_display_(/*14.54*/routes/*14.60*/.Auth.forgotPassword),format.raw/*14.80*/("""">"""),_display_(/*14.83*/Messages("signin.forgot")),format.raw/*14.108*/("""</a></p>
			</div>
		</fieldset>
	""")))}),format.raw/*17.3*/("""
""")))}))
      }
    }
  }

  def render(signInForm:Form[scala.Tuple3[String, String, Boolean]],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(signInForm)(request,messages)

  def f:((Form[scala.Tuple3[String, String, Boolean]]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (signInForm) => (request,messages) => apply(signInForm)(request,messages)

  def ref: this.type = this

}


}

/**/
object signIn extends signIn_Scope0.signIn
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 16:59:25 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/signIn.scala.html
                  HASH: d18a0b86f9af20ad3e613a13561adc646e444a3d
                  MATRIX: 594->1|849->99|877->168|903->186|942->188|971->192|982->196|1037->231|1066->235|1075->237|1114->268|1153->270|1183->274|1233->298|1277->322|1317->337|1326->339|1476->468|1508->474|1518->476|1664->600|1696->606|1706->608|1824->704|1856->710|1866->712|1921->758|1960->759|1989->761|2028->779|2061->781|2093->786|2157->824|2212->858|2241->859|2278->869|2293->875|2334->895|2364->898|2411->923|2479->961
                  LINES: 20->1|26->1|27->4|27->4|27->4|28->5|28->5|28->5|29->6|29->6|29->6|29->6|30->7|31->8|31->8|32->9|32->9|32->9|33->10|33->10|33->10|34->11|34->11|34->11|35->12|35->12|35->12|35->12|35->12|35->12|35->12|36->13|37->14|37->14|37->14|37->14|37->14|37->14|37->14|37->14|40->17
                  -- GENERATED --
              */
          
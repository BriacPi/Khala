
package views.html.auth

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object resetedPassword_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class resetedPassword extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[User,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(user: User)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.43*/("""
"""),_display_(/*2.2*/default("Reseted Password")/*2.29*/ {_display_(Seq[Any](format.raw/*2.31*/("""
	"""),format.raw/*3.2*/("""<p>"""),_display_(/*3.6*/Messages("forgot.reseted")),format.raw/*3.32*/("""</p>
	<a href=""""),_display_(/*4.12*/controllers/*4.23*/.routes.Application.index),format.raw/*4.48*/("""">"""),_display_(/*4.51*/Messages("go.index")),format.raw/*4.71*/("""</a>
""")))}))
      }
    }
  }

  def render(user:User,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(user)(messages)

  def f:((User) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (user) => (messages) => apply(user)(messages)

  def ref: this.type = this

}


}

/**/
object resetedPassword extends resetedPassword_Scope0.resetedPassword
              /*
                  -- GENERATED --
                  DATE: Fri Feb 12 15:31:33 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/auth/resetedPassword.scala.html
                  HASH: 314232b0f3bdfb259dd918d02bdd9accca1035cd
                  MATRIX: 559->1|695->42|723->45|758->72|797->74|826->77|855->81|901->107|944->124|963->135|1008->160|1037->163|1077->183
                  LINES: 20->1|25->1|26->2|26->2|26->2|27->3|27->3|27->3|28->4|28->4|28->4|28->4|28->4
                  -- GENERATED --
              */
          
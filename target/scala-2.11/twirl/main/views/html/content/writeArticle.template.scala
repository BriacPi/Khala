
package views.html.content

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object writeArticle_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

     object writeArticle_Scope1 {
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import models.{User,Article}

class writeArticle extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Form[Article],Lang,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*5.2*/(newArticleForm: Form[Article])(implicit lang:Lang):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.53*/("""




"""),_display_(/*10.2*/helper/*10.8*/.form(action = routes.ContentController.saveArticle(), args = 'class -> "newArticleForm")/*10.97*/{_display_(Seq[Any](format.raw/*10.98*/("""
  """),format.raw/*11.3*/("""<div class="centerFlex vertical-center" style="text-align: center" >
    <div class="page-header">

      <h1>"""),_display_(/*14.12*/Messages("Get on with your creative mind!")),format.raw/*14.55*/("""</h1>
    </div>


    """),format.raw/*18.66*/("""
    """),format.raw/*19.5*/("""<div class="content" style="margin-left:1em;">
      """),_display_(/*20.8*/if(newArticleForm.hasGlobalErrors)/*20.42*/ {_display_(Seq[Any](format.raw/*20.44*/("""
        """),format.raw/*21.9*/("""<div class="user__global-error-message vertical-center">
        """),_display_(/*22.10*/for(err <- newArticleForm.globalErrors.flatMap(_.messages)) yield /*22.69*/ {_display_(Seq[Any](format.raw/*22.71*/("""
          """),format.raw/*23.11*/("""<div class="error vertical-center errorRed">"""),_display_(/*23.56*/Messages(err)),format.raw/*23.69*/("""</div>
        """)))}),format.raw/*24.10*/("""
        """),format.raw/*25.9*/("""</div>
      """)))}),format.raw/*26.8*/("""
      """),format.raw/*27.7*/("""<div>
      """),_display_(/*28.8*/helper/*28.14*/.inputText(newArticleForm("title"), '_label -> Messages("article.title"))),format.raw/*28.87*/("""
      """),format.raw/*29.7*/("""</div>
      <div>
      """),_display_(/*31.8*/helper/*31.14*/.inputText(newArticleForm("content"), '_label -> Messages("article.content"))),format.raw/*31.91*/("""
      """),format.raw/*32.7*/("""</div>
      <div class="submit">
        <input type="submit" name="submit" value="""),_display_(/*34.51*/Messages("submit")),format.raw/*34.69*/(""">
      </div>
    </div>
    """)))}),format.raw/*37.6*/("""

"""),format.raw/*39.1*/("""</div>

"""))
      }
    }
  }

  def render(newArticleForm:Form[Article],lang:Lang): play.twirl.api.HtmlFormat.Appendable = apply(newArticleForm)(lang)

  def f:((Form[Article]) => (Lang) => play.twirl.api.HtmlFormat.Appendable) = (newArticleForm) => (lang) => apply(newArticleForm)(lang)

  def ref: this.type = this

}


}
}

/**/
object writeArticle extends writeArticle_Scope0.writeArticle_Scope1.writeArticle
              /*
                  -- GENERATED --
                  DATE: Mon Feb 15 22:41:12 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/content/writeArticle.scala.html
                  HASH: 787e3771abf9d096b0791046ee963150362e806d
                  MATRIX: 696->109|842->160|879->171|893->177|991->266|1030->267|1061->271|1202->385|1266->428|1321->516|1354->522|1435->577|1478->611|1518->613|1555->623|1649->690|1724->749|1764->751|1804->763|1876->808|1910->821|1958->838|1995->848|2040->863|2075->871|2115->885|2130->891|2224->964|2259->972|2313->1000|2328->1006|2426->1083|2461->1091|2574->1177|2613->1195|2677->1229|2708->1233
                  LINES: 25->5|30->5|35->10|35->10|35->10|35->10|36->11|39->14|39->14|43->18|44->19|45->20|45->20|45->20|46->21|47->22|47->22|47->22|48->23|48->23|48->23|49->24|50->25|51->26|52->27|53->28|53->28|53->28|54->29|56->31|56->31|56->31|57->32|59->34|59->34|62->37|64->39
                  -- GENERATED --
              */
          

package views.html.templates

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object default_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class default extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template9[String,Option[User],Boolean,String,Html,Html,Html,Html,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, loggedUser: Option[User], withMainTabs: Boolean = true, tab: String = "", styles: Html = Html(""), scripts: Html = Html(""), modals: Html = Html(""))(content: Html)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import views.html.tags.auth._
import controllers.routes
def /*6.2*/displayToggleLang/*6.19*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*6.23*/("""
"""),format.raw/*11.5*/("""
""")))};def /*14.2*/navbarContent/*14.15*/:play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*14.19*/("""
	"""),_display_(/*15.3*/if(withMainTabs)/*15.19*/ {_display_(Seq[Any](format.raw/*15.21*/("""
		"""),format.raw/*16.3*/("""<ul class="nav navbar-nav">
			<li class=""""),_display_(/*17.16*/("active".when(tab == "index"))),format.raw/*17.47*/(""""><a href=""""),_display_(/*17.59*/routes/*17.65*/.Application.index),format.raw/*17.83*/("""">"""),_display_(/*17.86*/Messages("topbar.index")),format.raw/*17.110*/("""</a></li>
			"""),_display_(/*18.5*/loggedUser/*18.15*/.map/*18.19*/ { implicit user =>_display_(Seq[Any](format.raw/*18.38*/("""
			"""),format.raw/*27.8*/("""
				"""),_display_(/*28.6*/withService("master")/*28.27*/ {_display_(Seq[Any](format.raw/*28.29*/("""
					"""),format.raw/*29.6*/("""<li class=""""),_display_(/*29.18*/("active".when(tab == "settings"))),format.raw/*29.52*/(""""><a href=""""),_display_(/*29.64*/routes/*29.70*/.Application.settings),format.raw/*29.91*/("""">Settings</a></li>
				""")))}),format.raw/*30.6*/("""
			""")))}),format.raw/*31.5*/("""
		"""),format.raw/*32.3*/("""</ul>
	""")))}),format.raw/*33.3*/("""
	"""),format.raw/*34.2*/("""<ul class="nav navbar-nav navbar-right">
		"""),_display_(/*35.4*/loggedUser/*35.14*/.map/*35.18*/ { user =>_display_(Seq[Any](format.raw/*35.28*/("""
		"""),format.raw/*36.3*/("""<li class="navbar-text text-right" style="margin: 5px 15px">
				<a href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					<span class ="glyphicon glyphicon-user"></span>
					"""),_display_(/*39.7*/user/*39.11*/.firstName),format.raw/*39.21*/("""
					"""),format.raw/*40.6*/("""<span class="glyphicon glyphicon-menu-down"></span>
				</a>
				<ul class="dropdown-menu" >
					<li><a href=""""),_display_(/*43.20*/routes/*43.26*/.Application.myProfile),format.raw/*43.48*/("""">"""),_display_(/*43.51*/Messages("topbar.myprofile")),format.raw/*43.79*/("""</a></li>
					<li><a href=""""),_display_(/*44.20*/routes/*44.26*/.Auth.signOut),format.raw/*44.39*/("""">"""),_display_(/*44.42*/Messages("signout")),format.raw/*44.61*/("""</a></li>
				</ul>
		</li>


		""")))}/*49.4*/.getOrElse/*49.14*/ {_display_(Seq[Any](format.raw/*49.16*/("""
			"""),_display_(/*50.5*/displayToggleLang),format.raw/*50.22*/("""
			"""),format.raw/*51.4*/("""<li class=""""),_display_(/*51.16*/("active".when(tab == "signin"))),format.raw/*51.48*/(""""><a href=""""),_display_(/*51.60*/routes/*51.66*/.Auth.signIn),format.raw/*51.78*/("""">"""),_display_(/*51.81*/Messages("signin")),format.raw/*51.99*/("""</a></li>
			<li class=""""),_display_(/*52.16*/("active".when(tab == "signup"))),format.raw/*52.48*/(""""><a href=""""),_display_(/*52.60*/routes/*52.66*/.Auth.startSignUp),format.raw/*52.83*/("""">"""),_display_(/*52.86*/Messages("signup")),format.raw/*52.104*/("""</a></li>
		""")))}),format.raw/*53.4*/("""
	"""),format.raw/*54.2*/("""</ul>
""")))};
Seq[Any](format.raw/*1.211*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*12.2*/("""

"""),format.raw/*55.2*/("""

"""),_display_(/*57.2*/main(title, navbarContent, styles, scripts, modals)/*57.53*/(content)))
      }
    }
  }

  def render(title:String,loggedUser:Option[User],withMainTabs:Boolean,tab:String,styles:Html,scripts:Html,modals:Html,content:Html,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(title,loggedUser,withMainTabs,tab,styles,scripts,modals)(content)(messages)

  def f:((String,Option[User],Boolean,String,Html,Html,Html) => (Html) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (title,loggedUser,withMainTabs,tab,styles,scripts,modals) => (content) => (messages) => apply(title,loggedUser,withMainTabs,tab,styles,scripts,modals)(content)(messages)

  def ref: this.type = this

}


}

/**/
object default extends default_Scope0.default
              /*
                  -- GENERATED --
                  DATE: Sun Feb 14 21:58:55 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/templates/default.scala.html
                  HASH: 68a5c4ff294a44514aa9c82fdd1dd05149027c22
                  MATRIX: 598->1|940->277|965->294|1045->298|1074->495|1100->503|1122->516|1203->520|1233->524|1258->540|1298->542|1329->546|1400->590|1452->621|1491->633|1506->639|1545->657|1575->660|1621->684|1662->699|1681->709|1694->713|1751->732|1783->1246|1816->1253|1846->1274|1886->1276|1920->1283|1959->1295|2014->1329|2053->1341|2068->1347|2110->1368|2166->1394|2202->1400|2233->1404|2272->1413|2302->1416|2373->1461|2392->1471|2405->1475|2453->1485|2484->1489|2730->1709|2743->1713|2774->1723|2808->1730|2950->1845|2965->1851|3008->1873|3038->1876|3087->1904|3144->1934|3159->1940|3193->1953|3223->1956|3263->1975|3319->2013|3338->2023|3378->2025|3410->2031|3448->2048|3480->2053|3519->2065|3572->2097|3611->2109|3626->2115|3659->2127|3689->2130|3728->2148|3781->2174|3834->2206|3873->2218|3888->2224|3926->2241|3956->2244|3996->2262|4040->2276|4070->2279|4118->210|4148->274|4177->498|4208->2287|4239->2292|4299->2343
                  LINES: 20->1|25->6|25->6|27->6|28->11|29->14|29->14|31->14|32->15|32->15|32->15|33->16|34->17|34->17|34->17|34->17|34->17|34->17|34->17|35->18|35->18|35->18|35->18|36->27|37->28|37->28|37->28|38->29|38->29|38->29|38->29|38->29|38->29|39->30|40->31|41->32|42->33|43->34|44->35|44->35|44->35|44->35|45->36|48->39|48->39|48->39|49->40|52->43|52->43|52->43|52->43|52->43|53->44|53->44|53->44|53->44|53->44|58->49|58->49|58->49|59->50|59->50|60->51|60->51|60->51|60->51|60->51|60->51|60->51|60->51|61->52|61->52|61->52|61->52|61->52|61->52|61->52|62->53|63->54|65->1|67->5|68->12|70->55|72->57|72->57
                  -- GENERATED --
              */
          

package views.html.templates

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,Html,Html,Html,Html,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String, navbarContent: Html = Html(""), styles: Html = Html(""), scripts: Html = Html(""), modals: Html = Html(""))(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import controllers.routes

Seq[Any](format.raw/*1.140*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.1*/("""<!DOCTYPE html>
<html>
	<head>
		<title>"""),_display_(/*8.11*/title),format.raw/*8.16*/("""</title>
		<link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.53*/routes/*9.59*/.MyAssets.img("favicon.ico")),format.raw/*9.87*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(/*10.48*/routes/*10.54*/.MyAssets.lib("bootstrap/css/bootstrap.min.css")),format.raw/*10.102*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(/*11.48*/routes/*11.54*/.MyAssets.css("main.css")),format.raw/*11.79*/("""">
		<script data-main=""""),_display_(/*12.23*/routes/*12.29*/.MyAssets.js("main")),format.raw/*12.49*/("""" src=""""),_display_(/*12.57*/routes/*12.63*/.MyAssets.lib("requirejs/require.js")),format.raw/*12.100*/("""" type="text/javascript"></script>
		"""),_display_(/*13.4*/styles),format.raw/*13.10*/("""
	"""),format.raw/*14.2*/("""</head>
	<body>
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href=""""),_display_(/*20.37*/routes/*20.43*/.Application.index),format.raw/*20.61*/("""">Khala</a>
					<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse" id="navbar-main">
					"""),_display_(/*29.7*/navbarContent),format.raw/*29.20*/("""
				"""),format.raw/*30.5*/("""</div>
			</div>
		</div>
		
		<div class="container">
			"""),_display_(/*35.5*/content),format.raw/*35.12*/("""
		"""),format.raw/*36.3*/("""</div>
		"""),_display_(/*37.4*/modals),format.raw/*37.10*/("""
		"""),_display_(/*38.4*/scripts),format.raw/*38.11*/("""
	"""),format.raw/*39.2*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,navbarContent:Html,styles:Html,scripts:Html,modals:Html,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title,navbarContent,styles,scripts,modals)(content)

  def f:((String,Html,Html,Html,Html) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title,navbarContent,styles,scripts,modals) => (content) => apply(title,navbarContent,styles,scripts,modals)(content)

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Sun Feb 14 15:28:38 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/templates/main.scala.html
                  HASH: b28b0de7f7371274d030236f4706e041840e2463
                  MATRIX: 560->1|819->139|849->171|877->173|947->217|972->222|1060->284|1074->290|1122->318|1200->369|1215->375|1285->423|1363->474|1378->480|1424->505|1477->531|1492->537|1533->557|1568->565|1583->571|1642->608|1707->647|1734->653|1764->656|2031->896|2046->902|2085->920|2488->1297|2522->1310|2555->1316|2645->1380|2673->1387|2704->1391|2741->1402|2768->1408|2799->1413|2827->1420|2857->1423
                  LINES: 20->1|25->1|27->4|28->5|31->8|31->8|32->9|32->9|32->9|33->10|33->10|33->10|34->11|34->11|34->11|35->12|35->12|35->12|35->12|35->12|35->12|36->13|36->13|37->14|43->20|43->20|43->20|52->29|52->29|53->30|58->35|58->35|59->36|60->37|60->37|61->38|61->38|62->39
                  -- GENERATED --
              */
          
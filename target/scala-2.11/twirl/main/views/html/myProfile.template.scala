
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object myProfile_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class myProfile extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[RequestHeader,User,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/()(implicit request: RequestHeader, user: User, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
import controllers.routes

Seq[Any](format.raw/*1.69*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/views/*5.7*/.html.templates.logged(title = "My account", tab = "myaccount")/*5.70*/ {_display_(Seq[Any](format.raw/*5.72*/("""

"""),_display_(/*7.2*/tags/*7.6*/.alertFromRequest(request, "success")),format.raw/*7.43*/("""

"""),format.raw/*9.1*/("""<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 1. Load libraries -->
        <!-- IE required polyfills, in this exact order -->
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/es6-shim/0.33.3/es6-shim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.20/system-polyfills.js"></script>

        <script src="https://code.angularjs.org/2.0.0-beta.6/angular2-polyfills.js"></script>
        <script src="https://code.angularjs.org/tools/system.js"></script>
        <script src="https://code.angularjs.org/tools/typescript.js"></script>
        <script src="https://code.angularjs.org/2.0.0-beta.6/Rx.js"></script>
        <script src="https://code.angularjs.org/2.0.0-beta.6/angular2.dev.js"></script>
        <script>

                System.config("""),format.raw/*24.31*/("""{"""),format.raw/*24.32*/("""
                    """),format.raw/*25.21*/("""//use typescript for compilation
                    transpiler: 'typescript',
                    //typescript compiler options
                    typescriptOptions: """),format.raw/*28.40*/("""{"""),format.raw/*28.41*/("""
                        """),format.raw/*29.25*/("""emitDecoratorMetadata: true
                    """),format.raw/*30.21*/("""}"""),format.raw/*30.22*/(""",

                    //packages defines our app package
                    packages: """),format.raw/*33.31*/("""{"""),format.raw/*33.32*/("""
                        """),format.raw/*34.25*/("""myprofile: """),format.raw/*34.36*/("""{"""),format.raw/*34.37*/("""
                            """),format.raw/*35.29*/("""main: './main.ts',
                            defaultExtension: 'ts'
                        """),format.raw/*37.25*/("""}"""),format.raw/*37.26*/("""
                    """),format.raw/*38.21*/("""}"""),format.raw/*38.22*/("""
                """),format.raw/*39.17*/("""}"""),format.raw/*39.18*/(""");
                System.import('/js/myprofile/main.ts')
                        .catch(console.error.bind(console));
        </script>
        <!-- 2. Configure SystemJS -->



</head>

        <!-- 3. Display the application -->
    <body>
        <my-app>Loading...</my-app>
    </body>



""")))}))
      }
    }
  }

  def render(request:RequestHeader,user:User,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply()(request,user,messages)

  def f:(() => (RequestHeader,User,Messages) => play.twirl.api.HtmlFormat.Appendable) = () => (request,user,messages) => apply()(request,user,messages)

  def ref: this.type = this

}


}

/**/
object myProfile extends myProfile_Scope0.myProfile
              /*
                  -- GENERATED --
                  DATE: Mon Feb 15 15:44:34 CET 2016
                  SOURCE: C:/Users/corpus/Desktop/khala/app/views/myProfile.scala.html
                  HASH: b22f3e15f4f12b863d9deea9eb9b1a0e3fb140be
                  MATRIX: 556->1|743->68|773->100|801->103|813->108|884->171|923->173|953->178|964->182|1021->219|1051->223|1954->1098|1983->1099|2033->1121|2232->1292|2261->1293|2315->1319|2392->1368|2421->1369|2540->1460|2569->1461|2623->1487|2662->1498|2691->1499|2749->1529|2873->1625|2902->1626|2952->1648|2981->1649|3027->1667|3056->1668
                  LINES: 20->1|25->1|27->4|28->5|28->5|28->5|28->5|30->7|30->7|30->7|32->9|47->24|47->24|48->25|51->28|51->28|52->29|53->30|53->30|56->33|56->33|57->34|57->34|57->34|58->35|60->37|60->37|61->38|61->38|62->39|62->39
                  -- GENERATED --
              */
          
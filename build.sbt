name := "Khala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-language:reflectiveCalls", "-language:postfixOps", "-language:implicitConversions")

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases",
  "Atlassian Releases" at "https://maven.atlassian.com/public/",
  Resolver.sonatypeRepo("snapshots")
)

routesGenerator := InjectedRoutesGenerator

includeFilter in(Assets, LessKeys.less) := "*.less"

excludeFilter in(Assets, LessKeys.less) := "_*.less"

pipelineStages := Seq(rjs, digest, gzip)

RjsKeys.mainModule := "main"

doc in Compile <<= target.map(_ / "none")


libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "org.webjars" % "requirejs" % "2.1.19",
  "com.mohiva" %% "play-silhouette" % "3.0.0",
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.4-P24", // Add bootstrap3 helpers and field constructors (http://play-bootstrap3.herokuapp.com/)
  "com.typesafe.play" %% "play-mailer" % "3.0.1",
  "org.webjars.bower" % "clean-blog" % "1.0.1"

)

//reactiveMongo
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "0.11.9",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.9",
  "com.typesafe.play" %% "anorm" % "2.4.0"
)


//Postgres
libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1207.jre7"
libraryDependencies += "org.jsoup" % "jsoup" % "1.8.3"






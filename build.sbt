name := "scala-xml-pp"

version := "0.0-SNAPSHOT"

scalaVersion := "2.12.7"

crossScalaVersions := Seq(
  // "2.10.7", // Doesn't have minimizeEmpty arg to scala.xml.PrettyPrinter
  "2.11.12",
  "2.12.7",
  "2.13.0-M4"
)

scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 12)) =>
      // https://tpolecat.github.io/2017/04/25/scalac-flags.html
      Seq(
        "-deprecation",
        "-encoding", "utf-8",
        "-explaintypes",
        "-feature",
        "-language:existentials",
        "-language:experimental.macros",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-unchecked",
        "-Xcheckinit",
        "-Xfatal-warnings",
        "-Xfuture",
        "-Xlint:adapted-args",
        "-Xlint:by-name-right-associative",
        "-Xlint:constant",
        "-Xlint:delayedinit-select",
        "-Xlint:doc-detached",
        "-Xlint:inaccessible",
        "-Xlint:infer-any",
        "-Xlint:missing-interpolator",
        "-Xlint:nullary-override",
        "-Xlint:nullary-unit",
        "-Xlint:option-implicit",
        "-Xlint:package-object-classes",
        "-Xlint:poly-implicit-overload",
        "-Xlint:private-shadow",
        "-Xlint:stars-align",
        "-Xlint:type-parameter-shadow",
        "-Xlint:unsound-match",
        "-Yno-adapted-args",
        "-Ypartial-unification",
        "-Ywarn-dead-code",
        // "-Ywarn-extra-implicit",
        "-Ywarn-inaccessible",
        "-Ywarn-infer-any",
        "-Ywarn-nullary-override",
        "-Ywarn-nullary-unit",
        "-Ywarn-numeric-widen",
        "-Ywarn-unused:implicits",
        "-Ywarn-unused:imports",
        "-Ywarn-unused:locals",
        "-Ywarn-unused:params",
        "-Ywarn-unused:patvars",
        "-Ywarn-unused:privates",
        "-Ywarn-value-discard"
      )
    case Some((2, 11)) => 
      // https://tpolecat.github.io/2014/04/11/scalac-flags.html
      Seq(
        "-deprecation",
        "-encoding", "UTF-8",
        "-feature",
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-unchecked",
        "-Xfatal-warnings",
        "-Xlint",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Ywarn-numeric-widen",
        "-Ywarn-value-discard",
        "-Xfuture",
        "-Ywarn-unused-import",
        "-Yno-predef",
        "-Yno-imports"
      )
    case _ =>
      Seq()
  }
}

libraryDependencies +=
  "com.github.scopt" %% "scopt" % "3.7.0"

libraryDependencies ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 10)) =>
      Seq()
    case _ =>
      Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.1.0"
      )
  }
}

import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin._
import ScalaJSPlugin.autoImport._

object Dependencies {

  lazy val settings = Seq(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.2",
      "com.github.japgolly.scalajs-react" %%% "core" % "0.10.4",
      "com.beachape" %%% "enumeratum" % "1.3.6"
    ),

    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % "0.14.3"
        /        "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "0.14.3"
        /         "react-dom.js"
        minified  "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM"
    )
  )

}


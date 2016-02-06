import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin._
import ScalaJSPlugin.autoImport._

object ScalaJS {
  lazy val settings = Seq(
    scalaJSStage in Global := FastOptStage,

    scalaJSUseRhino in Global := false,

    skip in packageJSDependencies := false,

    persistLauncher in Compile := true,

    persistLauncher in Test := false
  )
}


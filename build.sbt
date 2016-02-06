name := "photokatsu"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature","-deprecation", "-unchecked", "-Xlint")

enablePlugins(ScalaJSPlugin)

Dependencies.settings

ScalaJS.settings


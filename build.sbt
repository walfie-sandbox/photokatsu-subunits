name := "photokatsu"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature","-deprecation", "-unchecked", "-Xlint")

mainClass in (Compile, run) := Some("photokatsu.Photokatsu")


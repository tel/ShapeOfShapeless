name := "shapeOfShapeless"
organization := "com.jspha"
version := "0.0.1"

scalaVersion := "2.12.6"
scalacOptions ++= Seq(
  "-Yno-adapted-args",
  "-Ypartial-unification",
  "-Ywarn-numeric-widen",
  "-deprecation",
  "-target:jvm-1.8",
  "-unchecked",
  "-feature",
  "-deprecation"
)

val catsVersion = "1.0.1"
val shapelessVersion = "2.3.3"
val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "com.chuusai" %% "shapeless" % shapelessVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

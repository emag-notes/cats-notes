name := "scala-with-cats"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.4"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",                 // source files are in UTF-8
  "-deprecation",          // warn about use of deprecated APIs
  "-unchecked",            // warn about unchecked type parameters
  "-feature",              // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-Xlint",                // enable handy linter warnings
  "-Xfatal-warnings"       // turn compiler warnings into errors
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.3.1",
  "org.scalatest" %% "scalatest" % "3.2.3" % Test
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.2" cross CrossVersion.full)

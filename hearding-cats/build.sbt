val catsVersion      = "1.1.0"
val scalaTestVersion = "3.0.5"

lazy val root = (project in file("."))
  .settings(
    organization := "cats-notes",
    name := "hearding-cats",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.6",
    scalacOptions in (Compile, compile) ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint",
      "-Ywarn-dead-code",
      "-Ywarn-nullary-unit",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused",
      "-Ywarn-unused-import",
      "-Ypartial-unification"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    libraryDependencies ++= Seq(
      "org.typelevel"        %% "cats-core"  % catsVersion,
      "com.github.mpilquist" %% "simulacrum" % "0.12.0",
      "org.scalatest"        %% "scalatest"  % scalaTestVersion % Test
    )
  )

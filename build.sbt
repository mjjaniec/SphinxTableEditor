import Dependencies._

lazy val tableRegenerator = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.github.mjjaniec",
      scalaVersion := "2.12.1",
      version := "0.0.1"
    )),
    name := "TableRegenerator",
    mainClass in assembly := Some("com.github.mjjaniec.tableRegenerator.Main"),
    mainClass in (Compile, run) :=  Some("com.github.mjjaniec.tableRegenerator.Main"),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
      case _ => MergeStrategy.first
    },
    libraryDependencies ++= ProductionDependencies
  )
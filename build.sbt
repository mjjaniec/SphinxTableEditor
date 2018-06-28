import Dependencies._

lazy val tableRegenerator = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.github.mjjaniec",
      scalaVersion := "2.12.1",
      version := "0.0.1"
    )),
    name := "TableRegenerator",
    libraryDependencies ++= ProductionDependencies
  )
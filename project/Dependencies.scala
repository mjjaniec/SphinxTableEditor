import sbt._

object Dependencies {

  object V {
    val vaadin = "8.4.4"
    val stars = "3.0.0"
    val jetty = "9.4.6.v20170531"
    val avcommons = "1.28.1"
  }

  lazy val ProductionDependencies: List[ModuleID] = List(
    "com.vaadin" % "vaadin-server" % V.vaadin,
    "com.vaadin" % "vaadin-client-compiled" % V.vaadin,
    "com.vaadin" % "vaadin-client-compiler" % V.vaadin,
    "com.vaadin" % "vaadin-themes" % V.vaadin,

    "org.eclipse.jetty" % "jetty-server" % V.jetty,
    "org.eclipse.jetty" % "jetty-servlet" % V.jetty,

    "com.avsystem.commons" %% "commons-core" % V.avcommons
  )
}
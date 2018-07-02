package com.github.mjjaniec.tableRegenerator.ui

import com.avsystem.commons.SharedExtensions
import com.vaadin.ui.UI
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

class VaadinJettyServer(ui: Class[_ <: UI],
                        port: Int,
                        iconPath: String = "VAADIN/images/favicon.ico",
                        widgetSet: String = "com.vaadin.DefaultWidgetSet",
                        productionMode: Boolean = true) extends SharedExtensions {

  private val holder: ServletHolder = new ServletHolder().setup { h =>
    h.setServlet(new SimpleVaadinServlet(iconPath))
    h.setInitParameter("ui", ui.getName)
    h.setInitParameter("productionMode", "false")
    h.setInitParameter("widgetset", widgetSet)
    h.setInitOrder(0)
  }

  private val contextHandler: ServletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS).setup { c =>
    c.setClassLoader(classOf[VaadinJettyServer].getClassLoader)
    c.addServlet(holder, "/*")
    c.setResourceBase("src/main/WebContent")
  }

  private val server = new Server(port).setup { s =>
    s.setHandler(contextHandler)
  }

  def start(): Unit =
    server.start()

}
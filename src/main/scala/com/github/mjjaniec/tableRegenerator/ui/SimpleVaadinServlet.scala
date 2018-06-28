package com.github.mjjaniec.tableRegenerator.ui

import com.vaadin.server._


class SimpleVaadinServlet(iconPath: String) extends VaadinServlet with SessionInitListener {

  override protected def servletInitialized(): Unit = {
    super.servletInitialized()
    getService.addSessionInitListener(this)
  }

  override def sessionInit(event: SessionInitEvent): Unit = {
    event.getSession.addBootstrapListener(new BootstrapListener {
      override def modifyBootstrapFragment(response: BootstrapFragmentResponse): Unit = ()

      override def modifyBootstrapPage(response: BootstrapPageResponse): Unit = {
        response.getDocument.head.getElementsByAttributeValue("rel", "shortcut icon").attr("href", iconPath)
        response.getDocument.head.getElementsByAttributeValue("rel", "icon").attr("href", iconPath)
      }
    })
  }
}
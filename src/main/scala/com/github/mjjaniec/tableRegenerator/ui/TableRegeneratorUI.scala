package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.ui.main.MainView
import com.vaadin.annotations.Theme
import com.vaadin.server.{Page, VaadinRequest}
import com.vaadin.ui._

@Theme("Compact")
class TableRegeneratorUI extends UI {

  override def init(request: VaadinRequest): Unit = {
    setContent(new MainView)
  }
}
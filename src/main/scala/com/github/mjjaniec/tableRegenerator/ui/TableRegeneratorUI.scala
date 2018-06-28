package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.logic.{TableDrawer, TableParser}
import com.vaadin.server.{Page, VaadinRequest}
import com.vaadin.ui._
import com.vaadin.ui.themes.ValoTheme

import scala.util.Try

class TableRegeneratorUI extends UI {

  override def init(request: VaadinRequest): Unit = {
    val styles = Page.getCurrent.getStyles
    styles.add(".v-app .v-textarea { font-family: monospace; }")

    setSizeFull()

    setContent(new MainView)
  }
}
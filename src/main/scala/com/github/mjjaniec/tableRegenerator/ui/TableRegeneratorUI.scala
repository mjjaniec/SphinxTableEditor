package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.ui.main.MainView
import com.vaadin.server.{Page, VaadinRequest}
import com.vaadin.ui._

class TableRegeneratorUI extends UI {

  override def init(request: VaadinRequest): Unit = {
    val styles = Page.getCurrent.getStyles
    styles.add(".v-app .v-textarea { font-family: monospace; }")
    styles.add(".v-app .v-margin-left   {padding-left:   20px;}")
    styles.add(".v-app .v-margin-right  {padding-right:  20px;}")
    styles.add(".v-app .v-margin-top    {padding-top:    20px;}")
    styles.add(".v-app .v-margin-bottom {padding-bottom: 20px;}")

    setSizeFull()

    setContent(new MainView)
  }
}
package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.logic.{TableDrawer, TableParser}
import com.vaadin.server.Page.Styles
import com.vaadin.server.{Page, VaadinRequest}
import com.vaadin.ui._
import com.vaadin.ui.themes.ValoTheme

class TableRegeneratorUI extends UI {

  override def init(request: VaadinRequest): Unit = {
    val styles = Page.getCurrent.getStyles
    styles.add(".v-app .v-textarea { font-family: monospace; }")

    setSizeFull()

    val input = new TextArea("Input")
    input.setPlaceholder("Paste broken table here")
    input.setSizeFull()
    val regenerate = new Button("Regenerate")
    regenerate.addStyleName(ValoTheme.BUTTON_PRIMARY)

    val output = new TextArea("Output")
    output.setSizeFull()

    regenerate.addClickListener{_ =>
      val jaggedTable = input.getValue
      val tableData = TableParser.parse(jaggedTable)
      val prettyTable = TableDrawer.drawTable(tableData)
      output.setValue(prettyTable)
    }

    val layout = new VerticalLayout()
    layout.addComponentsAndExpand(input, regenerate, output)
    regenerate.setSizeUndefined()
    layout.setExpandRatio(regenerate, 0)
    setContent(layout)
  }
}
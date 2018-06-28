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

    val input = new TextArea("Input")
    input.setWordWrap(false)
    input.setPlaceholder("Paste broken table here")
    input.setSizeFull()
    val regenerate = new Button("Regenerate")
    regenerate.addStyleName(ValoTheme.BUTTON_PRIMARY)

    val maxCellWidth = new TextField()
    maxCellWidth.setPlaceholder("Max cell width")

    val output = new TextArea("Output")
    output.setWordWrap(false)
    output.setSizeFull()

    regenerate.addClickListener{_ =>
      val jaggedTable = input.getValue
      val tableData = TableParser.parse(jaggedTable)
      val prettyTable = TableDrawer.drawTable(tableData, Try(maxCellWidth.getValue.toInt).toOption)
      output.setValue(prettyTable)
    }

    val h = new HorizontalLayout(regenerate, maxCellWidth)

    input.setSizeFull()
    output.setSizeFull()

    val layout = new VerticalLayout()
    layout.addComponents(input, h, output)
    h.setWidthUndefined()
    layout.setExpandRatio(h, 0)
    layout.setExpandRatio(input, 1)
    layout.setExpandRatio(output, 1)
    layout.setSizeFull()
    setContent(layout)
  }
}
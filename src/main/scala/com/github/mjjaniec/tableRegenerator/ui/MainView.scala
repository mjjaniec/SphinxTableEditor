package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.logic.{TableDrawer, TableParser}
import com.vaadin.ui._
import com.vaadin.ui.themes.ValoTheme

import scala.util.Try

class MainView extends VerticalLayout {

  private val input = new TextArea("Input")
  private val output = new TextArea("Output")


  {
    input.setWordWrap(false)
    input.setPlaceholder("Paste broken table here")
    input.setValue("""+------------------------+------------+----------+----------+
                     || Header row, column 1   | Header 2   | Header 3 | Header 4 |
                     |+========================+============+==========+==========+
                     || body row 1, column 1   | column 2   | column 3 | column 4 |
                     |+------------------------+------------+----------+----------+
                     || body row 2             | ...        | ...      |          |
                     |+------------------------+------------+----------+----------+""".stripMargin)
    input.setSizeFull()
    val regenerate = new Button("Regenerate")
    regenerate.addStyleName(ValoTheme.BUTTON_PRIMARY)

    val maxCellWidth = new TextField()
    maxCellWidth.setPlaceholder("Max cell width")

    output.setWordWrap(false)
    output.setSizeFull()

    regenerate.addClickListener { _ =>
      val jaggedTable = input.getValue
      val tableData = TableParser.parse(jaggedTable)
      val prettyTable = TableDrawer.drawTable(tableData, Try(maxCellWidth.getValue.toInt).toOption)
      output.setValue(prettyTable)
    }

    val edit = new Button("Edit")
    edit.addClickListener { _ =>
      val jaggedTable = input.getValue
      val tableData = TableParser.parse(jaggedTable)
      getUI.setContent(new EditView(this, tableData))
    }

    val h = new HorizontalLayout(regenerate, maxCellWidth, new Label(""), edit)

    input.setSizeFull()
    output.setSizeFull()

    addComponents(input, h, output)
    h.setWidthUndefined()
    setExpandRatio(h, 0)
    setExpandRatio(input, 1)
    setExpandRatio(output, 1)
    setSizeFull()
  }

  def setOutput(output: String): Unit = {
    this.output.setValue(output)
  }

  def setInput(input: String): Unit = {
    this.input.setValue(input)
  }
}

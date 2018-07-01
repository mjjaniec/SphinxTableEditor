package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.logic.{TableDrawer, TableParser}
import com.github.mjjaniec.tableRegenerator.ui.modifiers.Mods
import com.vaadin.ui._

import scala.util.Try

class MainView extends VerticalLayout {

  private val input = Mods.textArea.caption("Input")
    .wordWrap(false)
    .placeholder("Paste broken table here")
    .sizeFull
    .get

  private val output = Mods.textArea.caption("Output")
    .wordWrap(false).sizeFull
    .get

  private val maxCellWidth = Mods.textField.placeholder("Max cell width").get

  private val regenerate = Mods.button.caption("Regenerate").primary
    .onClick { _ =>
      val jaggedTable = input.getValue
      val tableData = TableParser.parse(jaggedTable)
      val prettyTable = TableDrawer.drawTable(tableData, Try(maxCellWidth.getValue.toInt).toOption)
      output.setValue(prettyTable)
    }.get


  private val edit = Mods.button.caption("Edit").onClick { _ =>
    val jaggedTable = input.getValue
    val tableData = TableParser.parse(jaggedTable)
    getUI.setContent(new EditView(this, tableData))
  }.get

  Mods.mod(this)
    .sizeFull
    .add(input, 1)
    .add(Mods.horizontalLayout.widthUndefined
      .add(regenerate)
      .add(maxCellWidth)
      .add(Mods.hSpace("40px"))
      .add(edit).get, 0)
    .add(output, 1)


  def setOutput(output: String): Unit = {
    this.output.setValue(output)
  }

  def setInput(input: String): Unit = {
    this.input.setValue(input)
  }
}

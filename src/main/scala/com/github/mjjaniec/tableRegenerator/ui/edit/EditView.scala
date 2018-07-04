package com.github.mjjaniec.tableRegenerator.ui.edit

import com.github.mjjaniec.tableRegenerator.logic.{TableData, TableDrawer}
import com.github.mjjaniec.tableRegenerator.ui.main.MainView
import com.github.mjjaniec.tableRegenerator.ui.theme.Compact
import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.shared.ui.MarginInfo
import com.vaadin.ui._
import com.vaadin.ui.themes.ValoTheme

import scala.collection.mutable

class EditView(mainView: MainView, data: TableData) extends VerticalLayout {

  private val lengths = TableDrawer.computeLengts(data)
  private val rowsLayout = Vui.verticalLayout.margin(false).get
  private val rows: mutable.Buffer[DataRow] = mutable.Buffer.empty[DataRow]
  private val rowComponents = mutable.Map.empty[DataRow, Component]

  private def findIndex(row: DataRow): Int = rows.indexOf(row)

  private val buttonWidth = "40px"

  private def addButton(addAction: => Unit): Button = Vui
    .button.caption("+").friendly
    .width(buttonWidth)
    .disableTabStop
    .onClick(_ => addAction).get

  private def duplicateButton(duplicateAction: => Unit): Button = Vui
    .button.caption("2x").friendly
    .width(buttonWidth)
    .disableTabStop
    .onClick(_ => duplicateAction).get

  private def deleteButton(deleteAction: => Unit): Button = Vui
    .button.caption("x").danger
    .width(buttonWidth)
    .disableTabStop
    .onClick(_ => deleteAction).get

  private def createRowRowComponentPair(values: Seq[String]): (DataRow, Component) = {
    val row = new DataRow(values, lengths)

    val rowComponent = Vui.horizontalLayout.widthFull
      .add(row, 1)
      .add(Vui.verticalLayout.widthUndefined.heightFull
        .add(addButton(addEmptyRow(findIndex(row) + 1)))
        .add(duplicateButton(duplicateRow(row)))
        .add(deleteButton(deleteRow(row)))
        .get, 0)
      .get

    row -> rowComponent
  }

  private def doAddRow(row: DataRow, rowComponent: Component, index: Int): Unit = {
    rowComponents.put(row, rowComponent)
    rows.insert(index, row)
    rowsLayout.addComponent(rowComponent, index)
  }

  private def addEmptyRow(index: Int): Unit = {
    val (row, rowComponent) = createRowRowComponentPair(lengths.map(_ => ""))
    doAddRow(row, rowComponent, index)
  }

  private def duplicateRow(originalRow: DataRow): Unit = {
    val (row, rowComponent) = createRowRowComponentPair(originalRow.getRow)
    val index = findIndex(originalRow)
    doAddRow(row, rowComponent, index)
  }

  private def deleteRow(row: DataRow): Unit = {
    rowsLayout.removeComponent(rowComponents(row))
    rowComponents.remove(row)
    rows.remove(findIndex(row))
  }

  private val header: HeaderRow = new HeaderRow(data.headers, lengths)

  {
    data.rows.foreach { rowData =>
      val (row, rowComponent) = createRowRowComponentPair(rowData)
      rowComponents.put(row, rowComponent)
      rows.append(row)
      rowsLayout.addComponent(rowComponent)
    }

    setSizeFull()
    val save = Vui.button.caption("Save").primary.onClick { _ =>
      val result = TableData(header.getHeaders, rows.map(_.getRow))
      mainView.setOutput(TableDrawer.drawTable(result, None))
      mainView.setInput(TableDrawer.drawTable(result, None))
      getUI.setContent(mainView)
    }.get

    val cancel = Vui.button.caption("Cancel").onClick(_ => getUI.setContent(mainView)).get

    def withScrollMargin(component: Component): Component = Vui.horizontalLayout.widthFull.spacing(false)
      .add(component, 1, Vui.Align.BottomRight)
      .add(Vui.panel.sizeUndefined
        .style(ValoTheme.PANEL_BORDERLESS, Compact.Panel.ScrollAlwaysOn, Compact.Visibility.Hidden).get, 0)
      .get

    Vui.mod(this)
      .margin(new MarginInfo(true, false, true, true))
      .add(withScrollMargin(Vui.horizontalLayout.margin(false).widthFull
        .add(header, 1)
        .add(addButton(addEmptyRow(0)), 0)
        .get))
      .add(Vui.panel.sizeFull.content(rowsLayout)
        .style(ValoTheme.PANEL_BORDERLESS, Compact.Panel.ScrollAlwaysOn).get, 1)
      .add(withScrollMargin(Vui.horizontalLayout.add(cancel).add(save).sizeUndefined.get), 0)
  }

}

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
  private val rows: mutable.Buffer[RowView] = mutable.Buffer.empty[RowView]

  private val deleteAction: RowView => Unit = r => {
    rows.remove(rows.indexOf(r))
    rowsLayout.removeComponent(r)
  }

  private val addAction1: RowView => RowView = r => {
    val index = rows.indexOf(r) + 1
    val newRow = new RowView(lengths.map(_ => ""), lengths, deleteAction)
    rows.insert(index, newRow)
    rowsLayout.addComponent(newRow, index)
    newRow
  }

  private val addAction2: RowView => Unit =
    addAction1.andThen(r => r.setAddAction(addAction1.andThen(_.setAddAction(r => addAction1(r)))))

  private val addAction3: Unit => RowView = _ => {
    val newRow = new RowView(lengths.map(_ => ""), lengths, deleteAction)
    rows.insert(0, newRow)
    rowsLayout.addComponent(newRow, 0)
    newRow
  }

  val addAction4: Unit => Unit =
    addAction3.andThen(r => r.setAddAction(addAction1.andThen(_.setAddAction(r => addAction1(r)))))


  private val header: HeaderView = new HeaderView(data.headers, lengths, addAction4)


  {
    data.rows.map { row =>
      val rv = new RowView(row, lengths, deleteAction)
      rv.setAddAction(addAction2)
      rowsLayout.addComponent(rv)
      rv
    }.foreach(rows += _)

    setSizeFull()
    val save = Vui.button.caption("Save").primary.onClick { _ =>
      val result = TableData(header.getHeaders, rows.map(_.getRow))
      mainView.setOutput(TableDrawer.drawTable(result, None))
      mainView.setInput(TableDrawer.drawTable(result, None))
      getUI.setContent(mainView)
    }.get

    val cancel = Vui.button.caption("Cancel").onClick(_ => getUI.setContent(mainView)).get

    rowsLayout.addComponents(rows: _*)

    def withScrollMargin(component: Component): Component = Vui.horizontalLayout.widthFull.spacing(false)
      .add(component, 1)
      .add(Vui.panel.sizeUndefined
        .style(ValoTheme.PANEL_BORDERLESS, Compact.Panel.ScrollAlwaysOn, Compact.Visibility.Hidden).get, 0)
      .get

    Vui.mod(this)
      .margin(new MarginInfo(true, false, true, true))
      .add(withScrollMargin(header))
      .add(Vui.panel.sizeFull.content(rowsLayout)
        .style(ValoTheme.PANEL_BORDERLESS, Compact.Panel.ScrollAlwaysOn).get, 1)
      .add(withScrollMargin(Vui.horizontalLayout.add(cancel).add(save).sizeUndefined.get), 0)
  }

}

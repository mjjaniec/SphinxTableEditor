package com.github.mjjaniec.tableRegenerator.ui

import com.github.mjjaniec.tableRegenerator.logic.{TableData, TableDrawer}
import com.vaadin.ui.themes.ValoTheme
import com.vaadin.ui.{Alignment, Button, Panel, VerticalLayout}

class EditView(mainView: MainView, data: TableData) extends VerticalLayout {

  private val lengths = TableDrawer.computeLengts(data)
  private val header: HeaderView = new HeaderView(data.headers, lengths)
  private val rows: Seq[RowView] = data.rows.map(new RowView(_, lengths, () => ()))

  {
    setSizeFull()
    val save = new Button("Save")
    save.addStyleName(ValoTheme.BUTTON_PRIMARY)
    save.addClickListener { _ =>
      val result = TableData(header.getHeaders, rows.map(_.getRow))
      mainView.setOutput(TableDrawer.drawTable(result, None))
      mainView.setInput(TableDrawer.drawTable(result, None))
      getUI.setContent(mainView)
    }

    addComponent(header)
    val rowsPanel = new Panel()
    rowsPanel.setSizeFull()
    rowsPanel.addStyleName(ValoTheme.PANEL_BORDERLESS)
    val rowsLayout = new VerticalLayout(rows: _*)
    rowsLayout.setMargin(false)
    rowsPanel.setContent(rowsLayout)

    addComponent(rowsPanel)

    addComponent(save)
    setComponentAlignment(save, Alignment.BOTTOM_RIGHT)
    setExpandRatio(rowsPanel, 1)
    setExpandRatio(save, 0)
    setExpandRatio(header, 0)
  }

}

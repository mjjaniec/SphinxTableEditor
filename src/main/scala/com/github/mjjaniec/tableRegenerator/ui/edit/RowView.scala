package com.github.mjjaniec.tableRegenerator
package ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.ui._

class RowView(row: Seq[String], lengths: Seq[Int], deleteAction: RowView => Unit) extends HorizontalLayout {

  private val self = Vui.mod(this).widthFull

  private val add = new Button()

  private val editors: Seq[TextArea] = for {
    (cell, len) <- row zip lengths
  } yield {
    Vui.textArea.wordWrap(false).widthFull.value(cell).get.setup(self.add(_, len))
  }

  {
    self.add(Vui.verticalLayout.margin(false).sizeUndefined
    .add(Buttons.deleteButton(this, deleteAction), Vui.Align.MiddleCenter)
    .add(Buttons.addButton(_ => ()), Vui.Align.MiddleCenter)
    .get)

    setWidth("100%")
  }

  def setAddAction(action: RowView => Unit): Unit = {
    add.addClickListener(_ => action.apply(this))
  }


  def getRow: Seq[String] = editors.map(_.getValue)
}

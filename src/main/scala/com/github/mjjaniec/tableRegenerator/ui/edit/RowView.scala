package com.github.mjjaniec.tableRegenerator
package ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.ui.{Button, HorizontalLayout, TextArea}

class RowView(row: Seq[String], lengths: Seq[Int], deleteAction: RowView => Unit) extends HorizontalLayout {

  private val self = Vui.mod(this).widthFull

  private val addButton: Button = Buttons.addButton(_ => ())

  private val editors: Seq[TextArea] = for {
    (cell, len) <- row zip lengths
  } yield {
    Vui.textArea.wordWrap(false).widthFull.value(cell).get.setup(self.add(_, len))
  }

  {
    self.add(Vui.verticalLayout
      .margin(false).widthUndefined
      .add(Buttons.deleteButton(this, deleteAction), Vui.Align.MiddleCenter)
      .add(Buttons.duplicateButton(this, _ => ()), Vui.Align.MiddleCenter)
      .add(addButton, Vui.Align.MiddleCenter)
      .get)
  }

  def setAddAction(action: RowView => Unit): Unit = {
    addButton.addClickListener(_ => action.apply(this))
  }


  def getRow: Seq[String] = editors.map(_.getValue)
}

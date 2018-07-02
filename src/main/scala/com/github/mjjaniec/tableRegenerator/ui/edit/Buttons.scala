package com.github.mjjaniec.tableRegenerator.ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.ui.Button

object Buttons {
  def addButton(addAction: Unit => Unit ): Button = Vui
    .button.caption("+").friendly
    .onClick(_ => addAction.apply()).get

  def duplicateButton(rowView: RowView, duplicateAction: RowView => Unit): Button = Vui
    .button.caption("2x").friendly
    .onClick(_ => duplicateAction.apply(rowView)).get

  def deleteButton(rowView: RowView, deleteAction: RowView => Unit): Button = Vui
    .button.caption("x").danger
    .onClick(_ => deleteAction.apply(rowView)).get


}

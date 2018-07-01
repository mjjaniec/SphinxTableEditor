package com.github.mjjaniec.tableRegenerator.ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.ui.Button

object Buttons {
  def addButton(addAction: Unit => Unit ): Button = Vui
    .button.caption("+").friendly.small
    .width("37px").height("37px")
    .onClick(_ => addAction.apply()).get

  def deleteButton(rowView: RowView, deleteAction: RowView => Unit): Button = Vui
    .button.caption("x").danger.small
    .width("37px").height("37px")
    .onClick(_ => deleteAction.apply(rowView)).get


}

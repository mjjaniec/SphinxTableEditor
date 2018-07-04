package com.github.mjjaniec.tableRegenerator
package ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.Vui
import com.vaadin.ui.{HorizontalLayout, TextArea}

class DataRow(row: Seq[String], lengths: Seq[Int]) extends HorizontalLayout {

  private val self = Vui.mod(this).widthFull

  private val editors: Seq[TextArea] = for {
    (cell, len) <- row zip lengths
  } yield {
    Vui.textArea.wordWrap(false).widthFull.value(cell).get.setup(self.add(_, len))
  }

  def getRow: Seq[String] = editors.map(_.getValue)
}

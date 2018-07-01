package com.github.mjjaniec.tableRegenerator
package ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.{OrderedLayoutModifier, Vui}
import com.vaadin.ui.{HorizontalLayout, TextField}

class HeaderView(headers: Seq[String], lengths: Seq[Int], addAction: Unit => Unit) extends HorizontalLayout {

  private val self: OrderedLayoutModifier[HorizontalLayout] = Vui.mod(this).widthFull

  private val editors: Seq[TextField] = for {
    (h, l) <- headers zip lengths
  } yield {
    Vui.textField.value(h).widthFull.get.setup(self.add(_, l))
  }

  self.add(Buttons.addButton(addAction))


  def getHeaders: Seq[String] = editors.map(_.getValue)
}

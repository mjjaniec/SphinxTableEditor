package com.github.mjjaniec.tableRegenerator.ui.edit

import com.github.mjjaniec.tableRegenerator.ui.vui.{OrderedLayoutModifier, Vui}
import com.vaadin.ui.{HorizontalLayout, TextField}

class HeaderView(headers: Seq[String], lengths: Seq[Int], addAction: Unit => Unit) extends HorizontalLayout {

  private val self: OrderedLayoutModifier[HorizontalLayout] = Vui.mod(this).widthFull

  private val editors: Seq[TextField] = for {
    (h, l) <- headers zip lengths
  } yield {
    val tf = Vui.textField.value(h).widthFull.get
    self.add(tf, l)
    tf
  }

  {
    self.add(Vui.button.caption("+")
      .friendly.small.width("37px").height("37px")
      .onClick(_ => addAction.apply())
      .get)
  }

  def getHeaders: Seq[String] = editors.map(_.getValue)
}

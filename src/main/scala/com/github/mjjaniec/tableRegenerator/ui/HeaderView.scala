package com.github.mjjaniec.tableRegenerator.ui

import com.vaadin.ui.themes.ValoTheme
import com.vaadin.ui.{Button, HorizontalLayout, Label, TextField}

class HeaderView(headers: Seq[String], lengths: Seq[Int], addAction: Unit => Unit) extends HorizontalLayout {

  private val editors: Seq[TextField] = for {
    (h, l) <- headers zip lengths
  } yield {
    val tf = new TextField(null, h)
    tf.setWidth("100%")
    addComponent(tf)
    setExpandRatio(tf, l)
    tf
  }

  {
    setWidth("100%")
    val add = new Button("+")
    add.addStyleName(ValoTheme.BUTTON_FRIENDLY)
    add.addStyleName(ValoTheme.BUTTON_SMALL)
    add.setWidth("37px")
    add.setHeight("37px")
    add.addClickListener(_ => addAction.apply())
    addComponent(add)
  }

  def getHeaders: Seq[String] = editors.map(_.getValue)
}

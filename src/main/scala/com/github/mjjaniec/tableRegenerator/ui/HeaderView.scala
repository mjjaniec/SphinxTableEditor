package com.github.mjjaniec.tableRegenerator.ui

import com.vaadin.ui.{HorizontalLayout, Label, TextField}

class HeaderView(headers: Seq[String], lengths: Seq[Int]) extends HorizontalLayout {

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
    val placeholder = new Label()
    placeholder.setWidth("38px")
    addComponent(placeholder)
  }

  def getHeaders: Seq[String] = editors.map(_.getValue)
}

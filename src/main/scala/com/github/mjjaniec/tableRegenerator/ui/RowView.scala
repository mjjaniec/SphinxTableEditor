package com.github.mjjaniec.tableRegenerator.ui

import com.vaadin.ui.themes.ValoTheme
import com.vaadin.ui.{Button, HorizontalLayout, TextArea}

class RowView(row: Seq[String], lengths: Seq[Int], deleteAction: () => Unit) extends HorizontalLayout {
  private val editors: Seq[TextArea] = for {
    (cell, len) <- row zip lengths
  } yield {
    val tf = new TextArea(null, cell)
    tf.setWidth("100%")
    addComponent(tf)
    setExpandRatio(tf, len)
    tf
  }

  {

    val delete = new Button()
    delete.addClickListener(_ => deleteAction.apply)
    delete.addStyleName(ValoTheme.BUTTON_DANGER)

    setWidth("100%")
  }


  def getRow: Seq[String] = editors.map(_.getValue)
}

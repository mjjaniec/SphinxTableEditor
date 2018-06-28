package com.github.mjjaniec.tableRegenerator.ui

import com.vaadin.ui.themes.ValoTheme
import com.vaadin.ui._

class RowView(row: Seq[String], lengths: Seq[Int], deleteAction: RowView => Unit) extends HorizontalLayout {


  private val add = new Button()

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

    val vert = new VerticalLayout()
    vert.setMargin(false)
    vert.setSizeUndefined()

    val delete = new Button()
    delete.addClickListener(_ => deleteAction.apply(this))
    delete.addStyleName(ValoTheme.BUTTON_SMALL)
    delete.addStyleName(ValoTheme.BUTTON_DANGER)
    delete.setCaption("x")
    delete.setWidth("36px")

    add.addStyleName(ValoTheme.BUTTON_SMALL)
    add.addStyleName(ValoTheme.BUTTON_FRIENDLY)
    add.setCaption("+")
    add.setWidth("36px")

    vert.addComponents(delete, add)
    vert.setComponentAlignment(add, Alignment.MIDDLE_CENTER)
    vert.setComponentAlignment(delete, Alignment.MIDDLE_CENTER)
    vert.setHeight("100%")

    addComponent(vert)

    setWidth("100%")
  }

  def setAddAction(action: RowView => Unit): Unit = {
    add.addClickListener(_ => action.apply(this))
  }


  def getRow: Seq[String] = editors.map(_.getValue)
}

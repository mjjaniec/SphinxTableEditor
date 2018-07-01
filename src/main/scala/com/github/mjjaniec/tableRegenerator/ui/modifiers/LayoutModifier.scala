package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui.{Component, Layout}

class LayoutModifier[L <: Layout](l: L) extends ComponentModifier[L](l) {

  def add(c: Component): this.type = mod(_.addComponent(c))
  def add(opt: Option[_ <: Component]): this.type = opt match {
    case Some(c) => add(c)
    case None => this
  }


}

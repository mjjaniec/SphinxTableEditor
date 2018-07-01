package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.{AbstractOrderedLayout, Alignment, Component}

class OrderedLayoutModifier[L <: AbstractOrderedLayout](l: L) extends LayoutModifier[L](l) {


  def expandRation(c: Component, expandRation: Float): this.type = mod(_.setExpandRatio(c, expandRation))
  def alignment(c: Component, alignment: Alignment): this.type = mod(_.setComponentAlignment(c, alignment))

  def add(c:Component, expandRatio: Float): this.type = add(c).expandRation(c, expandRatio)
  def add(c:Component, alignment: Alignment): this.type = add(c).alignment(c, alignment)
  def add(c:Component, expandRatio: Float, alignment: Alignment): this.type =
    add(c).expandRation(c, expandRatio).alignment(c, alignment)
}

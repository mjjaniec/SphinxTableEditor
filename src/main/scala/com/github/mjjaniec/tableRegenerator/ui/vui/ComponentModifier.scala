package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.Component

class ComponentModifier[C <: Component](c: C) {

  def sizeFull: this.type = mod(_.setSizeFull())

  def sizeUndefined: this.type = mod(_.setSizeUndefined())

  def width(width: String): this.type = mod(_.setWidth(width))

  def height(height: String): this.type = mod(_.setHeight(height))

  def widthFull: this.type = width("100%")

  def heightFull: this.type = height("100%")

  def widthUndefined: this.type = mod(_.setWidthUndefined())

  def heightUndefined: this.type = mod(_.setHeightUndefined())

  def style(cssClassNames: String*): this.type = mod(_.addStyleNames(cssClassNames: _*))

  def caption(caption: String): this.type = mod(_.setCaption(caption))

  def get: C = c

  final protected def mod(modification: C => Unit): this.type = {
    modification(c)
    this
  }

}

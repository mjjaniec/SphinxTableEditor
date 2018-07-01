package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.{Component, Panel}

class PanelModifier(p: Panel) extends ComponentModifier[Panel](p) {

  def content(c: Component): this.type = mod(_.setContent(c))

}

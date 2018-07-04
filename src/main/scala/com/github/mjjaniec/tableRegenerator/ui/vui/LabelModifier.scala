package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Label

class LabelModifier(l: Label) extends ComponentModifier[Label](l) {

  def value(value: String): this.type = mod(_.setValue(value))

  def html: this.type = mod(_.setContentMode(ContentMode.HTML))

  def preformatted: this.type = mod(_.setContentMode(ContentMode.PREFORMATTED))

}

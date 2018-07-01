package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui.Label

class LabelModifier(l: Label) extends ComponentModifier[Label](l) {

  def value(value: String): this.type = mod(_.setValue(value))

}

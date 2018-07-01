package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui.AbstractField

class FieldModifier[T, F <: AbstractField[T]](f: F) extends ComponentModifier[F](f) {

  def value(value: T): this.type = mod(_.setValue(value))
}

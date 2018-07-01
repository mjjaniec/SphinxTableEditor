package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.AbstractTextField

class AbstractTextFieldModifier[F <: AbstractTextField](f: F) extends FieldModifier[String, F](f){
  def placeholder(placeholder: String): this.type = mod(_.setPlaceholder(placeholder))

}

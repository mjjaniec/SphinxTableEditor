package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui.TextArea

class TextAreaModifier(ta: TextArea) extends AbstractTextFieldModifier[TextArea] (ta) {

  def wordWrap(wordWrap: Boolean): this.type = mod(_.setWordWrap(wordWrap))

}

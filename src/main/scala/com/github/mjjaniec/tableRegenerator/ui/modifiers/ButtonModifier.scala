package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui.Button
import com.vaadin.ui.themes.ValoTheme

class ButtonModifier(b: Button) extends ComponentModifier[Button](b) {

  def primary: this.type = style(ValoTheme.BUTTON_PRIMARY)
  def friendly: this.type = style(ValoTheme.BUTTON_FRIENDLY)
  def danger: this.type = style(ValoTheme.BUTTON_DANGER)

  def onClick(listener: Button.ClickListener): this.type = mod(_.addClickListener(listener))

}

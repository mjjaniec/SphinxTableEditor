package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.Button
import com.vaadin.ui.themes.ValoTheme

class ButtonModifier(b: Button) extends ComponentModifier[Button](b) {

  def primary: this.type = style(ValoTheme.BUTTON_PRIMARY)
  def friendly: this.type = style(ValoTheme.BUTTON_FRIENDLY)
  def danger: this.type = style(ValoTheme.BUTTON_DANGER)

  def small: this.type = style(ValoTheme.BUTTON_SMALL)

  def onClick(listener: Button.ClickListener): this.type = mod(_.addClickListener(listener))

  def disableTabStop: this.type = mod(_.setTabIndex(-1))

}

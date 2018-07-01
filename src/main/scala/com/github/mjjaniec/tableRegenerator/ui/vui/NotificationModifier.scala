package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.themes.ValoTheme
import com.vaadin.ui.{Notification, UI}

class NotificationModifier(n: Notification) extends AbstractModifier[Notification](n) {

  def show(): Unit = {
    n.setDelayMsec(10000)
    n.show(UI.getCurrent.getPage)
  }

  def error: this.type = mod(_.setStyleName(ValoTheme.NOTIFICATION_SYSTEM))
  def warm: this.type = mod(_.setStyleName(ValoTheme.NOTIFICATION_WARNING))
  def info: this.type = mod(_.setStyleName(ValoTheme.NOTIFICATION_BAR))
  def success: this.type = mod(_.setStyleName(ValoTheme.NOTIFICATION_SUCCESS))

}

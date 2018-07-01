package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.{Alignment, Button, Component, HorizontalLayout, Label, Notification, Panel, TextArea, TextField, VerticalLayout}


object Vui {
  def mod(textArea: TextArea): TextAreaModifier = new TextAreaModifier(textArea)
  def textArea: TextAreaModifier = mod(new TextArea())

  def mod(textField: TextField): AbstractTextFieldModifier[TextField] = new AbstractTextFieldModifier[TextField](textField)
  def textField: AbstractTextFieldModifier[TextField] = mod(new TextField())

  def mod(button: Button): ButtonModifier = new ButtonModifier(button)
  def button: ButtonModifier = mod(new Button())

  def mod(h: HorizontalLayout): OrderedLayoutModifier[HorizontalLayout] = new OrderedLayoutModifier[HorizontalLayout](h)
  def horizontalLayout: OrderedLayoutModifier[HorizontalLayout] = mod(new HorizontalLayout())

  def mod(v: VerticalLayout): OrderedLayoutModifier[VerticalLayout] = new OrderedLayoutModifier[VerticalLayout](v)
  def verticalLayout: OrderedLayoutModifier[VerticalLayout] = mod(new VerticalLayout())

  def mod(l: Label): LabelModifier = new LabelModifier(l)
  def label: LabelModifier = mod(new Label())

  def mod(p: Panel): PanelModifier = new PanelModifier(p)
  def panel: PanelModifier = new PanelModifier(new Panel())

  def mod(n: Notification): NotificationModifier = new NotificationModifier(n)
  def notification(caption: String): NotificationModifier = new NotificationModifier(new Notification(caption))

  def hSpace(width: String): Component = label.width(width).get
  def vSpace(height: String): Component = label.height(height).widthUndefined.get

  object Align {
    val TopLeft: Alignment = Alignment.TOP_LEFT
    val TopCenter: Alignment = Alignment.TOP_CENTER
    val TopRight: Alignment = Alignment.TOP_RIGHT
    val MiddleLeft: Alignment = Alignment.MIDDLE_LEFT
    val MiddleCenter: Alignment = Alignment.MIDDLE_CENTER
    val MiddleRight: Alignment = Alignment.MIDDLE_RIGHT
    val BottomLeft: Alignment = Alignment.BOTTOM_LEFT
    val BottomCenter: Alignment = Alignment.BOTTOM_CENTER
    val BottomRight: Alignment = Alignment.BOTTOM_RIGHT
  }

}

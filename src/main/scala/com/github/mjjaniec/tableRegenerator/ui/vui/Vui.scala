package com.github.mjjaniec.tableRegenerator.ui.vui

import com.vaadin.ui.{Button, Component, HorizontalLayout, Label, TextArea, TextField, VerticalLayout}


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

  def hSpace(width: String): Component = label.width(width).get
  def vSpace(height: String): Component = label.height(height).widthUndefined.get

  object Alignment {
    import com.vaadin.ui.{Alignment => A}
    val TopLeft: A = A.TOP_LEFT
    val TopCenter: A = A.TOP_CENTER
    val TopRight: A = A.TOP_RIGHT
    val MiddleLeft: A = A.MIDDLE_LEFT
    val MiddleCenter: A = A.MIDDLE_CENTER
    val MiddleRight: A = A.MIDDLE_RIGHT
    val BottomLeft: A = A.BOTTOM_LEFT
    val BottomCenter: A = A.BOTTOM_CENTER
    val BottomRight: A = A.BOTTOM_RIGHT
  }

}

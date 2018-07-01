package com.github.mjjaniec.tableRegenerator.ui.modifiers

import com.vaadin.ui._

object Mods {
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

}

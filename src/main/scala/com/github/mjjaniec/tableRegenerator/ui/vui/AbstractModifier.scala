package com.github.mjjaniec.tableRegenerator.ui.vui

class AbstractModifier[T](t: T) {
  protected final def mod(action: T => Unit): this.type = {
    action(t)
    this
  }

  final def get: T = t

}

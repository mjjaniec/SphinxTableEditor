package com.github.mjjaniec.tableRegenerator

import com.github.mjjaniec.tableRegenerator.ui.{TableRegeneratorUI, VaadinJettyServer}

object Main {

  def main(args: Array[String]): Unit = {
    new VaadinJettyServer(classOf[TableRegeneratorUI], 8888).start()
  }
}

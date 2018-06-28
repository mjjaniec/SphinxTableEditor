package com.github.mjjaniec.tableRegenerator.logic

import java.io.{ByteArrayOutputStream, PrintStream}

import scala.collection.mutable


object TableDrawer {

  def printHorizontalLine(printer: PrintStream, lengths: Seq[Int], bold: Boolean): Unit = {
    printer.print("+")
    lengths.foreach(len => printer.print((if (bold) "=" else "-") * len + "+"))
    printer.println()
  }

  def drawTable(tableData: TableData): String = {
    import java.io.PrintStream
    import java.nio.charset.StandardCharsets
    val baos = new ByteArrayOutputStream

    val ps = new PrintStream(baos, true, "UTF-8")
    drawTable(ps, tableData)

    new String(baos.toByteArray, StandardCharsets.UTF_8)
  }


  def drawTable(printer: PrintStream, tableData: TableData): Unit = {

    def printHeaders(lengths: Seq[Int]): Unit = {
      printHorizontalLine(printer, lengths, bold = false)
      printer.print("|")
      tableData.headers zip lengths foreach { case (header, length) =>
        printer.print(" " + header + " " * (length - header.length - 1) + "|")
      }
      printer.println()
      printHorizontalLine(printer, lengths, bold = true)
    }

    def printContent(lengths: Seq[Int], multilineCells: Seq[Seq[Array[String]]]): Unit = {
      // Iteration in scala :O
      var rowNumber = 0
      while (rowNumber < multilineCells.head.length) {
        val height = multilineCells.map(_.apply(rowNumber).length).max
        var inRowLine = 0
        while (inRowLine < height) {
          printer.print("|")
          multilineCells zip lengths foreach { case (column, length) =>
            val content = if (column(rowNumber).length > inRowLine) column(rowNumber)(inRowLine) else ""
            printer.print(" " + content + " " * (length - content.length - 1) + "|")
          }
          printer.println()
          inRowLine += 1
        }
        printHorizontalLine(printer, lengths, bold = false)
        rowNumber += 1
      }

      printer.println()
      printer.println()
    }


    if (tableData.data.head.nonEmpty) {
      val transponsed = transponse(tableData.data)
      val multilineCells: Seq[Seq[Array[String]]] = transponsed.map(_.map(_.split("\n")))
      val lengths = multilineCells.map(_.iterator.map(_.map(_.length).max).max) zip tableData.headers.map(_.length) map {
        case (a, b) => Math.max(a, b) + 2
      }

      printHeaders(lengths)
      printContent(lengths, multilineCells)
    }
  }

  private def transponse[A](data: Seq[Seq[A]]): Seq[Seq[A]] = {
    val buffers = Vector.fill(data.head.size)(mutable.Buffer.empty[A])
    for (va <- data) {
      for ((a, idx) <- va.zipWithIndex) {
        buffers(idx) += a
      }
    }
    buffers.map(_.toVector)
  }
}
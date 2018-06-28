package com.github.mjjaniec.tableRegenerator.logic

import scala.collection.mutable
import scala.util.matching.Regex



object TableParser {

  val HorizontalLine: Regex = "\\+([-=]+\\+)+".r

  def parse(table: String): TableData = {


    val lines = table.split("\n")
      .toVector.map(_.trim)
      .dropWhile(!_.matches(HorizontalLine.regex))

    val columns = lines(0).count(_ == '+') - 1

    var target: Vector[StringBuilder] = null

    val data = mutable.Buffer.empty[Vector[String]]

    for (line <- lines) {
      line match {
        case HorizontalLine(_) =>
          if (target != null) {
            data += target.map(_.toString())
          }
          target = Vector.fill(columns)(new StringBuilder)
        case l =>
          val cells = l.split("(?<!\\|[a-z]{0,20})\\|(?![a-z]+\\|)").drop(1)

          for ((cel, idx) <- cells.take(columns).zipWithIndex) {
            val builder = target(idx)
            if (builder.nonEmpty) {
              builder.append("\n")
            }
            builder.append(cel.trim)
          }
      }
    }

    TableData(data.head, data.drop(1))
  }
}

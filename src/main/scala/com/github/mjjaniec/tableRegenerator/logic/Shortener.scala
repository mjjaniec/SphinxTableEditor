package com.github.mjjaniec.tableRegenerator.logic

import scala.annotation.tailrec
import scala.collection.mutable

object Shortener {

  private def findCutPlace(str: String, max: Int): Option[Int] = {
    if (str.length < max) {
      None
    } else {
      Some(str.take(max).lastIndexOf(' '))
        .filter(_ != -1)
        .orElse(Some(str.indexOf(' ')).filter(_ != -1))
    }
  }

  def shorten(str: String, maxLen: Int, nextLinePrefix: String = ""): Seq[String] = {
    val longestWord = str.split(' ').iterator.map(_.length).max
    val max = Math.max(maxLen, longestWord + nextLinePrefix.length)

    @tailrec
    def doShorten(str: String, acc: mutable.Buffer[String]): Seq[String] = {
      findCutPlace(str, max) match {
        case None =>
          acc.append(str)
          acc
        case Some(cutPoint) =>
          acc.append(str.substring(0, cutPoint))
          doShorten(str.substring(cutPoint + 1), acc)
      }
    }
    doShorten(str, mutable.Buffer.empty)
  }

}

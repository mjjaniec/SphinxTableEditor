package com.github.mjjaniec.tableRegenerator.logic

sealed trait ParseResult

case class ParseError(pos: Int, message: String) extends ParseResult
case class TableData(headers: Seq[String], rows: Seq[Seq[String]]) extends ParseResult


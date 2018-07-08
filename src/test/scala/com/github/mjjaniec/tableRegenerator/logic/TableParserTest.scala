package com.github.mjjaniec.tableRegenerator.logic

import org.scalatest.FlatSpec

class TableParserTest extends FlatSpec {

  "a simple table" should "be correctly parsed" in {
    val tableText =
      """+------------------------+------------+----------+----------+
        || Header row, column 1   | Header 2   | Header 3 | Header 4 |
        |+========================+============+==========+==========+
        || body row 1, column 1   | column 2   | column 3 | column 4 |
        |+------------------------+------------+----------+----------+
        || body row 2             | ...        | ...      |          |
        |+------------------------+------------+----------+----------+""".stripMargin
    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1, column 1", "column 2", "column 3", "column 4"),
        Seq("body row 2", "...", "...", "")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  "surrounding whitespaces" should "be ignored" in {
    val tableText =
      """
        |
        |
        |+------------------------+------------+----------+----------+
        || Header row, column 1   | Header 2   | Header 3 | Header 4 |
        |+========================+============+==========+==========+
        || body row 1, column 1   | column 2   | column 3 | column 4 |
        |+------------------------+------------+----------+----------+
        || body row 2             | ...        | ...      |          |
        |+------------------------+------------+----------+----------+
        |
        |   """.stripMargin
    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1, column 1", "column 2", "column 3", "column 4"),
        Seq("body row 2", "...", "...", "")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  "a jagged table" should "be correctly parsed" in {
    val tableText =
      """+------------------------+------------+----------+----------+
        || Header row, column 1         | Header 2   | Header 3 | Header 4 |
        |+======+============+==========+=====================+
        || body row 1, column 1                                  | column 2   | column 3 | column 4 |
        |+-----------------------+------------+----------+--+
        || body row 2     | ...        | ...      |          |
        |+------------------+------------+----------+----------+""".stripMargin
    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1, column 1", "column 2", "column 3", "column 4"),
        Seq("body row 2", "...", "...", "")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  "a table with sphinx special sings" should "be correctly parsed" in {
    val tableText =
      """+------------------------+------------+----------+----------+
        || Header row, column 1         | Header 2   | Header 3 | Header 4 |
        |+======+============+==========+=====================+
        || body row 1, column 1                                  | column 2   | column 3 | column 4 |
        |+-----------------------+------------+----------+--+
        || body row 2     | ...        | ...    peszek|maria  |zec|makota |xyz|      |
        |+------------------+------------+----------+----------+""".stripMargin
    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1, column 1", "column 2", "column 3", "column 4"),
        Seq("body row 2", "...", "...    peszek", "maria  |zec|makota |xyz|")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  "a table with valid multi-line cells" should "be correctly parsed" in {
    val tableText =
      """+----------------------+----------+----------+----------+
        || Header row, column 1 | Header 2 | Header 3 | Header 4 |
        |+======================+==========+==========+==========+
        || body row 1,          |          | column 3 | column 4 |
        || column 1 another row |          |          | mor data |
        |+----------------------+----------+----------+----------+
        || body row 2           | ...      | ...      |          |
        |+----------------------+----------+----------+----------+
        |
        |""".stripMargin

    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1,\ncolumn 1 another row", "", "column 3", "column 4\nmor data"),
        Seq("body row 2", "...", "...", "")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))

  }

  "another tale with valid multi-line cells" should "be correctly parsed" in {
    val tableText =
      """+----------------------+----------+----------+----------+
        || Header row, column 1 | Header 2 | Header 3 | Header 4 |
        |+======================+==========+==========+==========+
        || body row 1,          |          | column 3 | column 4 |
        || column 1 another row |          |          | mor data |
        |+----------------------+----------+----------+----------+
        || body row 2           | ...      | ...      |          |
        ||                      |          | ...      |          |
        ||                      |          | ...      |          |
        |+----------------------+----------+----------+----------+
        || body row 2           | ...      | ...      | x_X      |
        ||                      | ...      |          |          |
        ||                      | ...      |          |          |
        |+----------------------+----------+----------+----------+"""

    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1,\ncolumn 1 another row", "", "column 3", "column 4\nmor data"),
        Seq("body row 2", "...", "...\n...\n...", ""),
        Seq("body row 2", "...\n...\n...", "...", "x_X")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  "a table with invalid multi-line cells" should "be correctly parsed" in {
    val tableText =
      """+------------------------+------------+----------+----------+
        || Header row, column 1         | Header 2   | Header 3 | Header 4 |
        |+======+============+==========+=====================+
        || body row 1, column 1
        | the same cel | column 2 | column 3 |
        | >> |
        |+----------------+------------+----------+--+
        || body row 2     | ala
        | ma drucianego
        | kota | ...
        | kiedyś spotkałem marię
        | peszek| nic ciekawego      |
        |+------------------+------------+----------+----------+""".stripMargin
    val tableParsed = TableData(
      Seq("Header row, column 1", "Header 2", "Header 3", "Header 4"),
      Seq(
        Seq("body row 1, column 1\nthe same cel", "column 2", "column 3", "\n>>"),
        Seq("body row 2", "ala\nma drucianego\nkota", "...\nkiedyś spotakłem marię\npeszek", "nic ciekawego")
      ))

    expectTable(tableParsed, TableParser.parse(tableText))
  }

  def expectTable(expected: TableData, actual: ParseResult): Unit = actual match {
    case td: TableData => compareTables(expected, td)
    case x => fail(s"Expected table data, but got: $x")
  }

  def compareTables(expected: TableData, actual: TableData): Unit = {
    validateTableShape(actual)
    assert(expected.headers.length == actual.headers.length,
      s"Number of columns differ: expected ${expected.headers.length}, actual: ${actual.headers.length}")

    for ((eh, (ah, index)) <- expected.headers.zip(actual.headers.zipWithIndex)) {
      assert(eh == ah, s"${index + 1} header cell differs. Expected: $eh, actual $ah")
    }

    for ((er, (ar, rowIndex)) <- expected.rows.zip(actual.rows.zipWithIndex)) {
      for ((ec, (ac, columnIndex)) <- er.zip(ar.zipWithIndex)) {
        assert(ec == ac, s"Row: ${rowIndex + 1}, Column: ${columnIndex + 1} differs.")
      }
    }
  }

  def validateTableShape(table: TableData): Unit = {
    val columns = table.headers.length
    for ((row, index) <- table.rows.zipWithIndex) {
      assert(row.length == columns, s"Number of cells is wrong for ${index + 1} row. Expected $columns, actual ${row.length}. " +
        s"Invalid row: ${TableDrawer.drawTable(TableData(Seq.empty, Seq(row)))}")
    }
  }

}

package day04

import day04.Solution.{Board, Cell}

object App extends App {
  val source = io.Source.fromResource("day04.txt")

  val lines = source.getLines().toList

  val numbers = lines.head.split(",").toList.map(_.toInt)
  val boards = lines.tail
    .filter(_.nonEmpty)
    .grouped(5)
    .map(list => list.map(s => s.split(" ").toList.filter(_.nonEmpty)))
    .map(_.map(_.map(s => Cell(s.toInt))))
    .map(rows => Board(rows))

  val (winNumbers, winBoards) = Solution.playBingo(boards.toList, numbers)
  println(s"Win number: $winNumbers")
  println(s"Win boards: ${winBoards.foreach(b => b.rows.foreach(println))}")
  println(
    s"Bingo score: ${winBoards.map(Solution.calculateScore(_, winNumbers))}"
  )

  source.close()
}

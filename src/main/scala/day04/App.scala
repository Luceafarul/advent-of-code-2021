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
    .toList

  val (winNumber, winBoards) = Solution.playBingo(boards, numbers).head
  println(
    s"Bingo score: ${winBoards.map(Solution.calculateScore(_, winNumber))}"
  )
  val allWinners = Solution.playBingo(boards, numbers)
  val (lastWinNumber, lastWinBoards) = allWinners.tail.head
  println(
    s"Squid bingo score: ${lastWinBoards.map(Solution.calculateScore(_, lastWinNumber))}"
  )

  source.close()
}

package day02

import day02.Solution.Command

object App extends App {
  val source = io.Source.fromResource("day02.txt")

  val lines = source.getLines()
  val commands: List[Command] = lines.map { line =>
    val Array(direction, value) = line.split(" ")
    Command(direction, value.toInt)
  }.toList

  val result01: Solution.Position = Solution.calculatePosition(commands)
  println(result01 + " => " + result01.horizontal * result01.depth)

  val result02: Solution.Position = Solution.calculatePositionWithAim(commands)
  println(result02 + " => " + result02.horizontal * result02.depth)

  source.close()
}

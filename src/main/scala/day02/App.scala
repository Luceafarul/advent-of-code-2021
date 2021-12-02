package day02

import day02.Solution.Command

object App extends App {
  val source = io.Source.fromFile("src/main/resources/day02.txt")

  val lines = source.getLines()
  val commands: List[Command] = lines.map { line =>
    val commandAndValue = line.split(" ")
    Command(commandAndValue(0), commandAndValue(1).toInt)
  }.toList

  val result: Solution.Position = Solution.calculatePosition(commands)
  println(result + " => " + result.horizontal * result.depth)

  source.close()
}

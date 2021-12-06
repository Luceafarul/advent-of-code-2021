package day06

import day05.Solution.Path

object App extends App {
  val source = io.Source.fromResource("day05.txt")

  val lines = source.getLines().toList

  val initialState = lines.flatMap(_.split(",").map(_.toInt))

  println(
    s"Total lanternfish aafter 80 days: ${Solution.lanternfishAfterDays(initialState, 80)}"
  )

  source.close()
}

package day04

object App extends App {
  val source = io.Source.fromResource("day03.txt")

  val lines = source.getLines()

  println(s"Power consumptions is: ${}")

  source.close()
}

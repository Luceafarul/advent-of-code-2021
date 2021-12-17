package day18

object App extends App {
  val source = io.Source.fromResource("day17.txt")

  val lines = source.getLines().toList

  println(s": ${}")

  source.close()
}

package day15

object App extends App {
  val source = io.Source.fromResource("day15.txt")

  val lines = source.getLines().toList

  println(s": ${}")

  source.close()
}

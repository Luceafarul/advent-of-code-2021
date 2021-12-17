package day14

object App extends App {
  val source = io.Source.fromResource("day14.txt")

  val lines = source.getLines().toList

  println(s": ${}")

  source.close()
}

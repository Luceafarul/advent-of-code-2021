package day16

object App extends App {
  val source = io.Source.fromResource("day16.txt")

  val lines = source.getLines().toList

  println(s": ${}")

  source.close()
}

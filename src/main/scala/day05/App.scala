package day05

object App extends App {
  val source = io.Source.fromResource("day05.txt")

  val lines = source.getLines().toList

  println(s"Squid bingo score: ${}")

  source.close()
}

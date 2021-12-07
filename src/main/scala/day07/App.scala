package day07

object App extends App {
  val source = io.Source.fromResource("day07.txt")

  val lines = source.getLines().toList

  val initialState = lines.flatMap(_.split(",").map(_.toInt))

  println(s": ${}")

  source.close()
}

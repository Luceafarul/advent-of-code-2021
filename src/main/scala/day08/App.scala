package day08

object App extends App {
  val source = io.Source.fromResource("day07.txt")

  val lines = source.getLines().toList

  val positions = lines.flatMap(_.split(",").map(_.toInt))

  println(
    s": ${}\n" + ""
  )

  source.close()
}

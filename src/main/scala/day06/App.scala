package day06

object App extends App {
  val source = io.Source.fromResource("day06.txt")

  val lines = source.getLines().toList

  val initialState = lines.flatMap(_.split(",").map(_.toInt))

  println(
    s"Total lanternfish aafter 80 days: ${Solution.lanternfishAfterDays(initialState, 80).size}\n"
//    "" + s"Total lanternfish aafter 256 days: ${Solution.lanternfishAfterDays(initialState, 180).size}"
  )

  source.close()
}

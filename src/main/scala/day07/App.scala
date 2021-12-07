package day07

object App extends App {
  val source = io.Source.fromResource("day07.txt")

  val lines = source.getLines().toList

  val positions = lines.flatMap(_.split(",").map(_.toInt))

  println(
    s"Fuel spending: ${Solution.leastFuelSpendingForChangePosition(positions)}"
  )

  source.close()
}

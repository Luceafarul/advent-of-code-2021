package day11

object App extends App {
  val source = io.Source.fromResource("day11.txt")

  val lines = source.getLines().toList

  val energyLevels = lines.map(_.split("").map(_.toInt).toList)

  println(
    s"Total flashes: ${Solution.modelFlashes(Solution.Grid.of(energyLevels), steps = 100)}\n"
      + s"Level when all octopuses flash simultaneously: ${Solution
        .stepWhenAllOctopusFlash(Solution.Grid.of(energyLevels))}"
  )

  source.close()
}

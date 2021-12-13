package day13

object App extends App {
  val source = io.Source.fromResource("day13.txt")

  val lines = source.getLines().toList

  val (coordinates, foldCommands) = Solution.parse(lines)

  println(
    s"Visible dots after first fold: ${Solution.countVisibleDots(coordinates, foldCommands.head)}\n"
  )

  source.close()
}

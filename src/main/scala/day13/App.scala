package day13

object App extends App {
  val source = io.Source.fromResource("day13.txt")

  val lines = source.getLines().toList

  val (coordinates, foldCommands) = Solution.parse(lines)

  println(
    s"Visible dots after first fold: ${Solution.countVisibleDots(coordinates, foldCommands.head).size}\n"
  )

  val finalCoordinates = Solution.countVisibleDots(coordinates, foldCommands)
  val maxX = finalCoordinates.maxBy(_.x).x
  val maxY = finalCoordinates.maxBy(_.y).y
  (0 to maxY).foreach { y =>
    (0 to maxX).foreach { x =>
      if (finalCoordinates.contains(Solution.Coordinate(x, y))) print("#")
      else print(".")
    }
    println()
  }
  source.close()
}

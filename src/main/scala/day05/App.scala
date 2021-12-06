package day05

import day05.Solution.Path

object App extends App {
  val source = io.Source.fromResource("day05.txt")

  val lines = source.getLines().toList

  val paths =
    lines.map(s => s.replace("->", ",").split(",")).map { array =>
      val Array(x1, y1, x2, y2) = array.map(_.trim)
      Path(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
    }

  println(
    s"Total horizontal and vertical overlaps: ${Solution.overlapCount(Solution.buildDiagram(paths))}"
  )
  println(
    s"Total (include diagonal) overlaps: ${Solution
      .overlapCount(Solution.buildDiagram(paths, _ => true))}"
  )

  source.close()
}

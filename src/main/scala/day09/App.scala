package day09

object App extends App {
  val source = io.Source.fromResource("day09.txt")

  val lines = source.getLines().toList

  val points = lines.map(_.split("").map(_.toInt).toList)

  val heightmap = Solution.Heightmap.ofPoints(points)

  val lowestPoints = Solution.scanForLowestPoints(heightmap)

  val sumOfRiskLevels = Solution.riskLevelsSum(lowestPoints.toList.map(_.value))
  println(s"Sum of risk levels: $sumOfRiskLevels\n")

  val productOfTopThreeBasinsSizes =
    Solution.topThreeBasinsSizesProduct(heightmap, lowestPoints)
  println(s"Product of top three basins sizes: $productOfTopThreeBasinsSizes\n")

  source.close()
}

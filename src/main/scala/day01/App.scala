package day01

object App extends App {
  val source = io.Source.fromFile("src/main/resources/report.txt")

  val lines = source.getLines()

  source.close()

  println(Solution.depthMeasurementIncreasesCounter(lines.map(_.toInt).toList))
}

package day01

object App extends App {
  val source = io.Source.fromFile("src/main/resources/day01.txt")

  val lines = source.getLines()
  val numbers: List[Int] = lines.map(_.toInt).toList

  println(Solution.depthMeasurementIncreasesCounter(numbers))
  println(Solution.threeMeasurementSlidingWindowCounter(numbers))

  source.close()
}

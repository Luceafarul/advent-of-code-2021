package day03

import day03.Solution.BinaryNumber

object App extends App {
  val source = io.Source.fromResource("day03.txt")

  val lines = source.getLines()
  val report: List[BinaryNumber] = lines.toList.map(BinaryNumber)

  val gammaRate = Solution.gammaRate(report)
  val epsilonRate = Solution.epsilonRate(report)

  val CO2ScrubberRating = Solution.CO2ScrubberRating(report)
  val oxygenGeneratorRating = Solution.oxygenGeneratorRating(report)

  println(
    s"Power consumptions is: ${Solution.powerConsumption(gammaRate, epsilonRate)}"
  )
  println(
    s"Life support rating of the submarine is: ${Solution.lifeSupportRating(oxygenGeneratorRating, CO2ScrubberRating)}"
  )

  source.close()
}

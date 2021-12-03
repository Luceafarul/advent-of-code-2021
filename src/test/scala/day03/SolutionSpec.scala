package day03

import day03.Solution.BinaryNumber
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input = List(
      "00100",
      "11110",
      "10110",
      "10111",
      "10101",
      "01111",
      "00111",
      "11100",
      "10000",
      "11001",
      "00010",
      "01010"
    ).map(BinaryNumber)
    val gammaRate = BinaryNumber("10110")
    val epsilonRate = BinaryNumber("01001")
    val CO2ScrubberRating = BinaryNumber("01010")
    val oxygenGeneratorRating = BinaryNumber("10111")

    "correct calculate gamma rate" in {
      Solution.gammaRate(input) shouldBe gammaRate
    }

    "correct calculate epsilon rate" in {
      Solution.epsilonRate(input) shouldBe epsilonRate
    }

    "correct calculate power consumption" in {
      Solution.powerConsumption(gammaRate, epsilonRate) shouldBe 198
    }

    "correct calculate oxygen generator rating" in {
      Solution.oxygenGeneratorRating(input) shouldBe oxygenGeneratorRating
    }

    "correct calculate CO2 scrubber rating" in {
      Solution.CO2ScrubberRating(input) shouldBe CO2ScrubberRating
    }

    "correct calculate life support rating" in {
      Solution.lifeSupportRating(
        oxygenGeneratorRating,
        CO2ScrubberRating
      ) shouldBe 230
    }
  }
}

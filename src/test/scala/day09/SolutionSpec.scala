package day09

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input = List(
      List(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
      List(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
      List(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
      List(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
      List(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
    )

    val lowestPoints = List(1, 0, 5, 5)

    "found the lowest point of the area" in {
      Solution.scanLowestPoints(input) shouldBe List(1, 0, 5, 5)
    }

    "correct calculate sum of risk levels" in {
      Solution.riskLevelsSum(lowestPoints) shouldBe 16
    }
  }
}

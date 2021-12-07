package day07

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    "spend least fuel for change position as possible" in {
      val positions = List(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
      Solution.leastFuelSpendingForChangePosition(positions) shouldBe 37
    }
  }
}

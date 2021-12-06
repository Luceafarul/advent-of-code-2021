package day06

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val initialState = List(3, 4, 3, 1, 2)

    "correct evaluate state after one day" in {
      Solution
        .lanternfishAfterDays(initialState, 1) shouldBe List(2, 3, 2, 0, 1)
    }

    "correct evaluate state after two days" in {
      Solution
        .lanternfishAfterDays(initialState, 2) shouldBe List(1, 2, 1, 6, 0, 8)
    }

    "correct evaluate state after elevens days" in {
      Solution
        .lanternfishAfterDays(initialState, 1) shouldBe List(2, 3, 2, 0, 1)
    }

    "correct evaluate state after 18 days" in {
      Solution.lanternfishAfterDays(initialState, 18) shouldBe List(6, 0, 6, 4,
        5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8)
    }
  }
}

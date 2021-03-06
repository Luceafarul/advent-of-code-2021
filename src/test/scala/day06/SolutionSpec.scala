package day06

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val initialState = List(3, 4, 3, 1, 2)

    "correct evaluate state after 1 day" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 1) shouldBe List(
        2,
        3,
        2,
        0,
        1
      )
    }

    "correct evaluate state after 2 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 2) shouldBe List(1, 2, 1,
        6, 0, 8)
    }

    "correct evaluate state after 3 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 3) shouldBe List(0, 1, 0,
        5, 6, 7, 8)
    }

    "correct evaluate state after 4 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 4) shouldBe List(6, 0, 6,
        4, 5, 6, 7, 8, 8)
    }

    "correct evaluate state after 6 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 6) shouldBe List(4, 5, 4,
        2, 3, 4, 5, 6, 6, 7)
    }

    "correct evaluate state after 8 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 8) shouldBe List(2, 3, 2,
        0, 1, 2, 3, 4, 4, 5)
    }

    "correct evaluate state after 11 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 11) shouldBe List(6, 0, 6,
        4, 5, 6, 0, 1, 1, 2, 6, 7, 8, 8, 8)
    }

    "correct evaluate state after 13 days" in {
      Solution
        .lanternfishPopulationAfterDays(initialState, 13) shouldBe List(4, 5, 4,
        2, 3, 4, 5, 6, 6, 0, 4, 5, 6, 6, 6, 7, 7, 8, 8)
    }

    "correct evaluate state after 18 days" in {
      Solution.lanternfishPopulationAfterDays(initialState, 18) shouldBe List(6,
        0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8,
        8)
    }

    "correct evaluate total lanternfish after 1 day" in {
      Solution.lanternfishCountAfterDays(initialState, 1) shouldBe 5
    }

    "correct evaluate total lanternfish after 2 days" in {
      Solution.lanternfishCountAfterDays(initialState, 2) shouldBe 6
    }

    "correct evaluate total lanternfish after 3 days" in {
      Solution.lanternfishCountAfterDays(initialState, 3) shouldBe 7
    }

    "correct evaluate total lanternfish after 18 days" in {
      Solution.lanternfishCountAfterDays(initialState, 18) shouldBe 26
    }

    "correct evaluate total lanternfish after 80 days" in {
      Solution.lanternfishCountAfterDays(initialState, 80) shouldBe 5934
    }
  }
}

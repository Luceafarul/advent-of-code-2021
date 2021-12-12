package day11

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input = List(
      List(1, 1, 1, 1, 1),
      List(1, 9, 9, 9, 1),
      List(1, 9, 1, 9, 1),
      List(1, 9, 9, 9, 1),
      List(1, 1, 1, 1, 1)
    )

    val input01 = List(
      List(5, 4, 8, 3, 1, 4, 3, 2, 2, 3),
      List(2, 7, 4, 5, 8, 5, 4, 7, 1, 1),
      List(5, 2, 6, 4, 5, 5, 6, 1, 7, 3),
      List(6, 1, 4, 1, 3, 3, 6, 1, 4, 6),
      List(6, 3, 5, 7, 3, 8, 5, 4, 7, 8),
      List(4, 1, 6, 7, 5, 2, 4, 6, 4, 5),
      List(2, 1, 7, 6, 8, 4, 1, 7, 2, 1),
      List(6, 8, 8, 2, 8, 8, 1, 1, 3, 4),
      List(4, 8, 4, 6, 8, 4, 8, 5, 5, 4),
      List(5, 2, 8, 3, 7, 5, 1, 5, 2, 6)
    )

    val output = List(
      List(3, 4, 5, 4, 3),
      List(4, 0, 0, 0, 4),
      List(5, 0, 0, 0, 5),
      List(4, 0, 0, 0, 4),
      List(3, 4, 5, 4, 3)
    )

    "correct evaluate grid state after 1 step" in {
      val grid = Solution.Grid.of(input)
      val gridAfterStep1 = Solution.Grid.of(output)
      Solution.modelFlashes(grid, steps = 1)._2 shouldBe 9
    }

    "correct evaluate grid state after 2 steps" in {
      val grid = Solution.Grid.of(input01)
      Solution.modelFlashes(grid, steps = 2)._2 shouldBe 35
    }

    "correct evaluate grid state after 3 steps" in {
      val grid = Solution.Grid.of(input01)
      Solution.modelFlashes(grid, steps = 3)._2 shouldBe 80
    }

    "correct evaluate grid state after 10 steps" in {
      val grid = Solution.Grid.of(input01)
      Solution.modelFlashes(grid, steps = 10)._2 shouldBe 204
    }

    "correct evaluate grid state after 100 steps" in {
      val grid = Solution.Grid.of(input01)
      Solution.modelFlashes(grid, steps = 100)._2 shouldBe 1656
    }
  }
}

package day02

import day02.Solution.{Command, Position}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    "correct calculate horizontal position and depth" in {
      val input = List(
        Command("forward", 5),
        Command("down", 5),
        Command("forward", 8),
        Command("up", 3),
        Command("down", 8),
        Command("forward", 2)
      )

      Solution.calculatePosition(input) shouldBe Position(15, 10)
    }

    "correct calculate horizontal position and depth with aim" in {
      val input = List(
        Command("forward", 5),
        Command("down", 5),
        Command("forward", 8),
        Command("up", 3),
        Command("down", 8),
        Command("forward", 2)
      )

      Solution.calculatePositionWithAim(input) shouldBe Position(15, 60, 10)
    }
  }
}

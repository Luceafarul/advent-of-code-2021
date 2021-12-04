package day04

import day04.Solution.{Board, Cell}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val board3 = Board(
      List(
        List(
          Cell(14, marked = true),
          Cell(21, marked = true),
          Cell(17, marked = true),
          Cell(24, marked = true),
          Cell(4, marked = true)
        ),
        List(Cell(10), Cell(16), Cell(15), Cell(9, marked = true), Cell(19)),
        List(Cell(18), Cell(8), Cell(23, marked = true), Cell(26), Cell(20)),
        List(
          Cell(22),
          Cell(11, marked = true),
          Cell(13),
          Cell(6),
          Cell(5, marked = true)
        ),
        List(
          Cell(2, marked = true),
          Cell(0, marked = true),
          Cell(12),
          Cell(3),
          Cell(7, marked = true)
        )
      )
    )

    "correct calculate win condition on board" in {
      Solution.boardWin(board3) shouldBe true
    }
  }
}

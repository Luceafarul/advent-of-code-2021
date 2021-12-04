package day04

import day04.Solution.{Board, Cell, calculateScore}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val numbers = List(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6,
      15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)

    val cleanBoards = List(
      Board(
        List(
          List(Cell(3), Cell(15), Cell(0), Cell(2), Cell(22)),
          List(Cell(9), Cell(18), Cell(13), Cell(17), Cell(5)),
          List(Cell(19), Cell(8), Cell(7), Cell(25), Cell(23)),
          List(Cell(20), Cell(11), Cell(10), Cell(24), Cell(4)),
          List(Cell(14), Cell(21), Cell(16), Cell(12), Cell(6))
        )
      ),
      Board(
        List(
          List(Cell(14), Cell(21), Cell(17), Cell(24), Cell(4)),
          List(Cell(10), Cell(16), Cell(15), Cell(9), Cell(19)),
          List(Cell(18), Cell(8), Cell(23), Cell(26), Cell(20)),
          List(Cell(22), Cell(11), Cell(13), Cell(6), Cell(5)),
          List(Cell(2), Cell(0), Cell(12), Cell(3), Cell(7))
        )
      )
    )

    val board2 = Board(
      List(
        List(Cell(3), Cell(15, marked = true), Cell(0), Cell(2), Cell(22)),
        List(Cell(9), Cell(18, marked = true), Cell(13), Cell(17), Cell(5)),
        List(Cell(19), Cell(8, marked = true), Cell(7), Cell(25), Cell(23)),
        List(Cell(20), Cell(11, marked = true), Cell(10), Cell(24), Cell(4)),
        List(Cell(14), Cell(21, marked = true), Cell(16), Cell(12), Cell(6))
      )
    )

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

    "correct calculate win condition on board by row" in {
      Solution.boardWin(board3) shouldBe true
    }

    "correct calculate win condition on board by column" in {
      Solution.boardWin(board2) shouldBe true
    }

    "correct play game and stop when any board win" in {
      Solution.playBingo(cleanBoards, numbers) shouldBe (24, List(board3))
    }

    "correct calculate game score" in {
      val (winNumber, boards) = Solution.playBingo(cleanBoards, numbers)
      boards.map(Solution.calculateScore(_, winNumber)) shouldBe List(4512)
    }
  }
}

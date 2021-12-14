package day13

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input =
      List(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0",
        "",
        "fold along y=7",
        "fold along x=5"
      )

    val coordinates = List(
      Solution.Coordinate(6, 10),
      Solution.Coordinate(0, 14),
      Solution.Coordinate(9, 10),
      Solution.Coordinate(0, 3),
      Solution.Coordinate(10, 4),
      Solution.Coordinate(4, 11),
      Solution.Coordinate(6, 0),
      Solution.Coordinate(6, 12),
      Solution.Coordinate(4, 1),
      Solution.Coordinate(0, 13),
      Solution.Coordinate(10, 12),
      Solution.Coordinate(3, 4),
      Solution.Coordinate(3, 0),
      Solution.Coordinate(8, 4),
      Solution.Coordinate(1, 10),
      Solution.Coordinate(2, 14),
      Solution.Coordinate(8, 10),
      Solution.Coordinate(9, 0)
    )
    val folds = List(
      Solution.FoldCommand("y", 7),
      Solution.FoldCommand("x", 5)
    )

    "correct parse coordinates and folds" in {
      Solution.parse(input) shouldBe (coordinates, folds)
    }

    "calculate dots count after apply first fold" in {
      val res = Solution.countVisibleDots(coordinates, folds.head)
      println(s"Result 1: $res")
      res.size shouldBe 17
    }

    "calculate dots count after apply second fold" in {
      val res = Solution
        .countVisibleDots(
          Solution.countVisibleDots(coordinates, folds.head),
          folds.last
        )
      println(s"Result 2: $res")
      res.size shouldBe 16
    }
  }
}

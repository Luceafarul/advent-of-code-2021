package day05

import day05.Solution.{Coordinates, Path}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val paths = List(
      Path(0, 9, 5, 9),
      Path(8, 0, 0, 8),
      Path(9, 4, 3, 4),
      Path(2, 2, 2, 1),
      Path(7, 0, 7, 4),
      Path(6, 4, 2, 0),
      Path(0, 9, 2, 9),
      Path(3, 4, 1, 4),
      Path(0, 0, 8, 8),
      Path(5, 5, 8, 2)
    )

    "correct evaluation of diagram from input coordinates" in {
      Solution.buildDiagram(paths).filter { case (_, counter) =>
        counter != 0
      } shouldBe Map(
        Coordinates(0, 9) -> 2,
        Coordinates(1, 9) -> 2,
        Coordinates(2, 9) -> 2,
        Coordinates(3, 9) -> 1,
        Coordinates(4, 9) -> 1,
        Coordinates(5, 9) -> 1,
        Coordinates(2, 1) -> 1,
        Coordinates(2, 2) -> 1,
        Coordinates(7, 0) -> 1,
        Coordinates(7, 1) -> 1,
        Coordinates(7, 2) -> 1,
        Coordinates(7, 3) -> 1,
        Coordinates(1, 4) -> 1,
        Coordinates(2, 4) -> 1,
        Coordinates(3, 4) -> 2,
        Coordinates(4, 4) -> 1,
        Coordinates(5, 4) -> 1,
        Coordinates(6, 4) -> 1,
        Coordinates(7, 4) -> 2,
        Coordinates(8, 4) -> 1,
        Coordinates(9, 4) -> 1
      )
    }

    "correct calculate overlap count" in {
      val diagram = Map(
        Coordinates(1, 1) -> 2,
        Coordinates(1, 2) -> 1
      )

      Solution.overlapCount(diagram) shouldBe 1
      Solution.overlapCount(Solution.buildDiagram(paths)) shouldBe 5
    }

    "correct evaluation path coverages" in {
      val pathOne = Path(1, 1, 1, 3)
      val pathTwo = Path(9, 7, 7, 7)

      Solution.pathByCoordinates(pathOne) should contain only (
        Coordinates(1, 1),
        Coordinates(1, 2),
        Coordinates(1, 3)
      )

      Solution.pathByCoordinates(pathTwo) should contain only (
        Coordinates(9, 7),
        Coordinates(8, 7),
        Coordinates(7, 7)
      )

      Solution.pathByCoordinates(Path(8, 0, 0, 8)) should contain only (
        Coordinates(8, 0),
        Coordinates(7, 1),
        Coordinates(6, 2),
        Coordinates(5, 3),
        Coordinates(4, 4),
        Coordinates(3, 5),
        Coordinates(2, 6),
        Coordinates(1, 7),
        Coordinates(0, 8)
      )

      Solution.pathByCoordinates(Path(0, 0, 8, 8)) should contain only (
        Coordinates(0, 0),
        Coordinates(1, 1),
        Coordinates(2, 2),
        Coordinates(3, 3),
        Coordinates(4, 4),
        Coordinates(5, 5),
        Coordinates(6, 6),
        Coordinates(7, 7),
        Coordinates(8, 8)
      )

      Solution.pathByCoordinates(Path(6, 4, 2, 0)) should contain only (
        Coordinates(2, 0),
        Coordinates(3, 1),
        Coordinates(4, 2),
        Coordinates(5, 3),
        Coordinates(6, 4),
      )
    }
  }
}

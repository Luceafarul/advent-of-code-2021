package day08

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input = List(
      "fdgacbe cefdb cefbgd gcbe",
      "fcgedb cgb dgebacf gc",
      "cg cg fdcagb cbg",
      "efabcd cedba gadfec cb",
      "gecf egdcabf bgf bfgea",
      "gebdcfa ecba ca fadegcb",
      "cefg dcbef fcge gbcadfe",
      "ed bcgafe cdgba cbgef",
      "gbdfcae bgc cg cgb",
      "fgae cfgab fg bagce"
    ).flatMap(_.split(" "))

    "correct evaluate count of numbers 1, 4, 7, 8" in {
      Solution.filterEasyDigits(input).size shouldBe 26
    }
  }
}

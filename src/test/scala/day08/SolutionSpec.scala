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

    val input01 = Map(
      List(
        "acedgfb",
        "cdfbe",
        "gcdfa",
        "fbcad",
        "dab",
        "cefabd",
        "cdfgeb",
        "eafb",
        "cagedb",
        "ab"
      ) -> List("cdfeb", "fcadb", "cdfeb", "cdbaf")
    )

    val input02 = List(
      "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
      "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
      "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
      "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
      "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
      "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
      "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
      "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
      "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
      "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    ).map { s =>
      val Array(k, v) = s.split("\\|")
      k.split(" ").map(_.trim).filterNot(_.isBlank).toList -> v
        .split(" ")
        .map(_.trim)
        .filterNot(_.isBlank)
        .toList
    }.toMap

    "correct evaluate count of numbers 1, 4, 7, 8" in {
      Solution.filterUniqueLengthDigits(input).size shouldBe 26
    }

    "correct decode numbers from one-line input" in {
      Solution.decodedNumbers(input01).sum shouldBe 5353
    }

    "correct decode numbers from test input" in {
      Solution.decodedNumbers(input02).sum shouldBe 61229
    }
  }
}

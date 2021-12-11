package day10

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    val input = List(
      "[({(<(())[]>[[{[]{<()<>>",
      "[(()[<>])]({[<{<<[]>>(",
      "{([(<{}[<>[]}>{[]{[(<()>",
      "(((({<>}<{<{<>}{[]{[]{}",
      "[[<[([]))<([[{}[[()]]]",
      "[{[{({}]{}}([{[{{{}}([]",
      "{<[[]]>}<{[{[{[]{()[[[]",
      "[<(<(<(<{}))><([]([]()",
      "<{([([[(<>()){}]>(<<{{",
      "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    val incorrectClosingChar = List('}', ')', ']', ')', '>')

    "found all firsts incorrect closing character" in {
      Solution.firstIncorrectClosingCharacters(
        input
      ) shouldBe incorrectClosingChar
    }

    "calculate total syntax error score" in {
      Solution.totalSyntaxErrorScore(incorrectClosingChar) shouldBe 26397
    }
  }
}

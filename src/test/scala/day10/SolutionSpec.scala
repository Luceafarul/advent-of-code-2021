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

    //List(
    // List([, (, {, (, [, [, {, {),
    // List((, {, [, <, {, (),
    // List((, (, (, (, <, {, <, {, {),
    // List(<, {, [, {, [, {, {, [, [),
    // List(<, {, (, [)
    // )

    //List(
    // List(], ), }, ), ], ], }, }),
    // List(), }, ], >, }, )),
    // List(), ), ), ), >, }, >, }, }),
    // List(>, }, ], }, ], }, }, ], ]),
    // List(>, }, ), ])
    // )
    val closeChars = List(
      List('}', '}', ']', ']', ')', '}', ')', ']'),
      List(')', '}', '>', ']', '}', ')'),
      List('}', '}', '>', '}', '>', ')', ')', ')', ')'),
      List(']', ']', '}', '}', ']', '}', ']', '}', '>'),
      List(']', ')', '}', '>')
    )

    "found all firsts incorrect closing character" in {
      Solution.firstIncorrectClosingCharacters(
        input
      ) shouldBe incorrectClosingChar
    }

    "calculate total syntax error score" in {
      Solution.totalSyntaxErrorScore(incorrectClosingChar) shouldBe 26397
    }

    "found all incomplete opened characters" in {
      println(Solution.completeOpenCharacters(input))
      Solution
        .completeOpenCharacters(input) shouldBe closeChars
    }

    "calculate completion total score" in {
      Solution.completionTotalSyntaxErrorScore(closeChars) shouldBe List(
        288957,
        5566,
        1480781,
        995444,
        294
      )
    }
  }
}

package day10

import scala.annotation.tailrec

/*
--- Day 10: Syntax Scoring ---

You ask the submarine to determine the best route out of the deep-sea cave, but it only replies:
Syntax error in navigation subsystem on line: all of them
All of them?! The damage is worse than you thought.
You bring up a copy of the navigation subsystem (your puzzle input).

The navigation subsystem syntax is made of several lines containing chunks.
There are one or more chunks on each line, and chunks contain zero or more other chunks.
Adjacent chunks are not separated by any delimiter; if one chunk stops,
the next chunk (if any) can immediately start.
Every chunk must open and close with one of four legal pairs of matching characters:

If a chunk opens with (, it must close with ).
If a chunk opens with [, it must close with ].
If a chunk opens with {, it must close with }.
If a chunk opens with <, it must close with >.

So, () is a legal chunk that contains no other chunks, as is [].
More complex but valid chunks include ([]), {()()()}, <([{}])>, [<>({}){}[([])<>]],
and even (((((((((()))))))))).

Some lines are incomplete, but others are corrupted. Find and discard the corrupted lines first.

A corrupted line is one where a chunk closes with the wrong character - that is,
where the characters it opens and closes with do not form one of the four legal pairs listed above.

Examples of corrupted chunks include (], {()()()>, (((()))}, and <([]){()}[{}]).
Such a chunk can appear anywhere within a line,
and its presence causes the whole line to be considered corrupted.

For example, consider the following navigation subsystem:
[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
Some of the lines aren't corrupted, just incomplete; you can ignore these lines for now.
The remaining five lines are corrupted:
{([(<{}[<>[]}>{[]{[(<()> - Expected ], but found } instead.
[[<[([]))<([[{}[[()]]] - Expected ], but found ) instead.
[{[{({}]{}}([{[{{{}}([] - Expected ), but found ] instead.
[<(<(<(<{}))><([]([]() - Expected >, but found ) instead.
<{([([[(<>()){}]>(<<{{ - Expected ], but found > instead.
Stop at the first incorrect closing character on each corrupted line.

Did you know that syntax checkers actually have contests to see
who can get the high score for syntax errors in a file? It's true!
To calculate the syntax error score for a line, take the first illegal
character on the line and look it up in the following table:

): 3 points.
]: 57 points.
}: 1197 points.
>: 25137 points.
In the above example, an illegal ) was found twice (2*3 = 6 points), an illegal ]
was found once (57 points), an illegal } was found once (1197 points),
and an illegal > was found once (25137 points).
So, the total syntax error score for this file is 6+57+1197+25137 = 26397 points!

Find the first illegal character in each corrupted line of the navigation subsystem.
What is the total syntax error score for those errors?
 */
object Solution {
  private val openChunk0 = '('
  private val openChunk1 = '['
  private val openChunk2 = '{'
  private val openChunk3 = '<'
  private val closeChunk0 = ')'
  private val closeChunk1 = ']'
  private val closeChunk2 = '}'
  private val closeChunk3 = '>'

  private val points = Map(
    ')' -> 3,
    ']' -> 57,
    '}' -> 1197,
    '>' -> 25137
  )

  def firstIncorrectClosingCharacters(input: List[String]): List[Char] = {
    def correctClosedChar(open: Char, close: Char): Boolean = {
      open match {
        case '(' => close == closeChunk0
        case '[' => close == closeChunk1
        case '{' => close == closeChunk2
        case '<' => close == closeChunk3
      }
    }

    def isOpenChar(ch: Char): Boolean = {
      ch == openChunk0 || ch == openChunk1 || ch == openChunk2 || ch == openChunk3
    }

    def firstIncorrectClosingCharacter(s: String): Option[Char] = {
      @tailrec
      def loop(s: String, opens: List[Char]): Option[Char] = {
        if (s.isEmpty) None
        else {
          if (isOpenChar(s.head)) loop(s.tail, opens :+ s.head)
          else if (correctClosedChar(opens.last, s.head))
            loop(s.tail, opens.init)
          else Some(s.head)
        }
      }

      loop(s, opens = List.empty)
    }

    input.foldLeft(List.empty[Char]) { (acc, s) =>
      firstIncorrectClosingCharacter(s) match {
        case Some(value) => acc :+ value
        case None        => acc
      }
    }
  }

  def totalSyntaxErrorScore(incorrectClosingCharacters: List[Char]): Int = {
    incorrectClosingCharacters.map { ch =>
      points.get(ch) match {
        case Some(score) => score
        case None        => 0
      }
    }.sum
  }
}

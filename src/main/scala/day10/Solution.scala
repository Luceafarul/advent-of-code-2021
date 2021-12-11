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

--- Part Two ---

Now, discard the corrupted lines. The remaining lines are incomplete.

Incomplete lines don't have any incorrect characters - instead,
they're missing some closing characters at the end of the line.
To repair the navigation subsystem, you just need to figure out
the sequence of closing characters that complete all open chunks in the line.

You can only use closing characters (), ], }, or >), and you must add them in the
correct order so that only legal pairs are formed and all chunks end up closed.

In the example above, there are five incomplete lines:
[({(<(())[]>[[{[]{<()<>> - Complete by adding }}]])})].
[(()[<>])]({[<{<<[]>>( - Complete by adding )}>]}).
(((({<>}<{<{<>}{[]{[]{} - Complete by adding }}>}>)))).
{<[[]]>}<{[{[{[]{()[[[] - Complete by adding ]]}}]}]}>.
<{([{{}}[<[[[<>{}]]]>[]] - Complete by adding ])}>.
Did you know that autocomplete tools also have contests? It's true!
The score is determined by considering the completion string character-by-character.
Start with a total score of 0. Then, for each character, multiply the total score by 5 and
then increase the total score by the point value given for the character in the following table:
): 1 point.
]: 2 points.
}: 3 points.
>: 4 points.
So, the last completion string above - ])}> - would be scored as follows:
Start with a total score of 0.
Multiply the total score by 5 to get 0, then add the value of ] (2) to get a new total score of 2.
Multiply the total score by 5 to get 10, then add the value of ) (1) to get a new total score of 11.
Multiply the total score by 5 to get 55, then add the value of } (3) to get a new total score of 58.
Multiply the total score by 5 to get 290, then add the value of > (4) to get a new total score of 294.
The five lines' completion strings have total scores as follows:

}}]])})] - 288957 total points.
)}>]}) - 5566 total points.
}}>}>)))) - 1480781 total points.
]]}}]}]}> - 995444 total points.
])}> - 294 total points.
Autocomplete tools are an odd bunch: the winner is found by sorting all of the scores and
then taking the middle score. (There will always be an odd number of scores to consider.)
In this example, the middle score is 288957 because there are the same number
of scores smaller and larger than it.

Find the completion string for each incomplete line, score the completion strings,
and sort the scores. What is the middle score?
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

  private val wrongClosedPoints = Map(
    closeChunk0 -> 3,
    closeChunk1 -> 57,
    closeChunk2 -> 1197,
    closeChunk3 -> 25137
  )

  private val incompleteClosedPoints: Map[Char, Long] = Map(
    closeChunk0 -> 1L,
    closeChunk1 -> 2L,
    closeChunk2 -> 3L,
    closeChunk3 -> 4L
  )

  def firstIncorrectClosingCharacters(input: List[String]): List[Char] = {
    def firstIncorrectClosingCharacter(s: String): Option[Char] = {
      @tailrec
      def loop(s: String, opens: List[Char]): Option[Char] = {
        if (s.isEmpty) None
        else if (isOpenChar(s.head)) loop(s.tail, opens :+ s.head)
        else if (correctClosedChar(opens.last, s.head)) loop(s.tail, opens.init)
        else Some(s.head)
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

  def completeOpenCharacters(input: List[String]): List[List[Char]] = {
    def incompleteOpenedCharacters(s: String): List[Char] = {
      @tailrec
      def loop(s: String, opens: List[Char]): List[Char] = {
        if (s.isEmpty) opens
        else if (isOpenChar(s.head)) loop(s.tail, opens :+ s.head)
        else if (correctClosedChar(opens.last, s.head)) loop(s.tail, opens.init)
        else List.empty
      }

      loop(s, opens = List.empty)
    }

    def completeOpenedCharacters(xs: List[Char]): List[Char] = {
      xs.reverse.map {
        case '(' => closeChunk0
        case '[' => closeChunk1
        case '{' => closeChunk2
        case '<' => closeChunk3
      }
    }

    input
      .map { s => completeOpenedCharacters(incompleteOpenedCharacters(s)) }
      .filterNot(_.isEmpty)
  }

  private def correctClosedChar(open: Char, close: Char): Boolean = {
    open match {
      case '(' => close == closeChunk0
      case '[' => close == closeChunk1
      case '{' => close == closeChunk2
      case '<' => close == closeChunk3
    }
  }

  private def isOpenChar(ch: Char): Boolean = {
    ch == openChunk0 || ch == openChunk1 || ch == openChunk2 || ch == openChunk3
  }

  def completionTotalSyntaxErrorScore(input: List[List[Char]]): List[Long] = {
    input.map { xs =>
      xs.foldLeft(0L) { (acc, ch) =>
        (acc * 5L) + incompleteClosedPoints.getOrElse(ch, 0L)
      }
    }
  }

  def totalSyntaxErrorScore(incorrectClosingCharacters: List[Char]): Int = {
    incorrectClosingCharacters.map { ch =>
      wrongClosedPoints.get(ch) match {
        case Some(score) => score
        case None        => 0
      }
    }.sum
  }
}

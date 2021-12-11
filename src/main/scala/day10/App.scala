package day10

object App extends App {
  val source = io.Source.fromResource("day10.txt")

  val lines = source.getLines().toList

  val completionScores: List[Long] = Solution
    .completionTotalSyntaxErrorScore(Solution.completeOpenCharacters(lines))
    .sorted
  println(
    s"Total syntax error score: ${Solution
      .totalSyntaxErrorScore(Solution.firstIncorrectClosingCharacters(lines))}\n"
      + s"Middle completion score: ${completionScores(completionScores.size / 2)}"
  )

  source.close()
}

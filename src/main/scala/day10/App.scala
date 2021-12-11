package day10

object App extends App {
  val source = io.Source.fromResource("day10.txt")

  val lines = source.getLines().toList

  println(s"Total syntax error score: ${Solution
    .totalSyntaxErrorScore(Solution.firstIncorrectClosingCharacters(lines))}\n")

  source.close()
}

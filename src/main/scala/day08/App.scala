package day08

object App extends App {
  val source = io.Source.fromResource("day08.txt")

  val lines = source.getLines().toList

  val numbers = lines.flatMap { s =>
    val Array(_, numbers) = s.split("\\|")
    numbers.split(" ").map(_.trim).filter(s => !s.isBlank).toList
  }

  val number = lines.map { s =>
    val Array(k, v) = s.split("\\|")
    k.split(" ").map(_.trim).filterNot(_.isBlank).toList -> v
      .split(" ")
      .map(_.trim)
      .filterNot(_.isBlank)
      .toList
  }.toMap

  println(
    s"Count of easy digits: ${Solution.filterEasyDigits(numbers).size}\n" +
      s"Sum of decoded digits: ${Solution.decodedNumbers(number).sum}"
  )

  source.close()
}

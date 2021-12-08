package day08

object App extends App {
  val source = io.Source.fromResource("day08.txt")

  val lines = source.getLines().toList

  val numbers = lines.flatMap { s =>
    val Array(_, numbers) = s.split("\\|")
    numbers.split(" ").map(_.trim).filter(s => !s.isBlank).toList
  }

  println(
    s"Count of easy digits: ${Solution.filterEasyDigits(numbers).size}\n" + ""
  )

  source.close()
}

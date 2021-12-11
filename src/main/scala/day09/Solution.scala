package day09

import java.time.LocalTime
import scala.annotation.tailrec

/*
--- Day 9: Smoke Basin ---

These caves seem to be lava tubes. Parts are even still volcanically active;
small hydrothermal vents release smoke into the caves that slowly settles like rain.

If you can model how the smoke flows through the caves,
you might be able to avoid it and be that much safer.
The submarine generates a heightmap of the floor of
the nearby caves for you (your puzzle input).

Smoke flows to the lowest point of the area it's in.
For example, consider the following heightmap:
2[1]9994321[0]
3987894921
98[5]6789892
8767896789
989996[5]678

Each number corresponds to the height of a particular location,
where 9 is the highest and 0 is the lowest a location can be.

Your first goal is to find the low points - the locations that are lower than
any of its adjacent locations. Most locations have four adjacent locations
(up, down, left, and right); locations on the edge or corner of the map have three or
 two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)

In the above example, there are four low points, all highlighted:
two are in the first row (a 1 and a 0), one is in the third row (a 5),
and one is in the bottom row (also a 5). All other locations on the heightmap
have some lower adjacent location, and so are not low points.

The risk level of a low point is 1 plus its height. In the above example,
the risk levels of the low points are 2, 1, 6, and 6. The sum of the risk levels
of all low points in the heightmap is therefore 15.

Find all of the low points on your heightmap.
What is the sum of the risk levels of all low points on your heightmap?
 */
object Solution {
  type Points = List[List[Int]]
  final case class Heightmap(coordinates: List[Coordinate]) {
    def markAsChecked(coordinate: Coordinate): Heightmap = Heightmap {
      coordinates.filterNot(c =>
        c.x == coordinate.x && c.y == coordinate.y
      ) :+ coordinate.markChecked
    }
    def markAsChecked(cs: List[Coordinate]): Heightmap =
      cs.map(markAsChecked).last
    def notChecked(): List[Coordinate] = coordinates.filterNot(_.checked)
    def checked(): List[Coordinate] = coordinates.filter(_.checked)
  }

  object Heightmap {
    def ofPoints(points: Points): Heightmap = Heightmap {
      for {
        p <- points
        x <- points.indices
        y <- p.indices
      } yield Coordinate(x, y, points(x)(y))
    }
  }

  final case class Coordinate(
      x: Int,
      y: Int,
      value: Int,
      checked: Boolean = false
  ) {
    def markChecked: Coordinate = this.copy(checked = true)
  }

  def scanLowestPoints(input: Heightmap): List[Int] = {
    @tailrec
    def checkAdjacent(
        coordinate: Coordinate,
        heightmap: Heightmap,
        minsToCheck: Set[Coordinate] = Set.empty,
        mins: Set[Coordinate] = Set.empty
    ): (Heightmap, Set[Coordinate]) = {
      println(
        s"[${LocalTime.now}]: inside checkAdjacent... $minsToCheck and $mins"
      )
      val adjacent = heightmap.coordinates
        .filter { c =>
          (c.x == coordinate.x - 1 && c.y == coordinate.y) || // up
          (c.x == coordinate.x + 1 && c.y == coordinate.y) || // down
          (c.x == coordinate.x && c.y == coordinate.y - 1) || // left
          (c.x == coordinate.x && c.y == coordinate.y + 1) // right
        }
      // found min
      val min = (adjacent :+ coordinate).minBy(_.value)

      // if coordinate - min, return min and updated heightmap
      val res = (minsToCheck ++ (adjacent :+ coordinate))
        .filter(_.value <= min.value)
        .diff(mins)
      if (res.isEmpty) {
        val newHeightmap = heightmap.markAsChecked(adjacent :+ coordinate)
        (newHeightmap, mins)
      } else if (min == coordinate) {
        val newHeightmap = heightmap.markAsChecked(adjacent :+ coordinate)
        (newHeightmap, mins + min.markChecked)
      } else {
        val newHeightmap = heightmap.markAsChecked(adjacent :+ coordinate)
        checkAdjacent(
          res.head,
          newHeightmap,
          minsToCheck ++ res.tail,
          mins
        )
      }
    }

    @tailrec
    def loop(input: Heightmap, acc: Set[Coordinate]): Set[Coordinate] = {
      if (input.notChecked().isEmpty) acc
      else {
        val coordinateToCheck = input.notChecked().head
        val (heightmap, min) =
          checkAdjacent(
            coordinateToCheck,
            input,
            mins = acc
          )

//        println(
//          s"coordinateToCheck: $coordinateToCheck => min $min and heightmap: $heightmap"
//        )
        loop(heightmap, min)
      }
    }

    val res = loop(input, Set.empty)
    res.toList.map(_.value)
  }

  def riskLevelsSum(lowestPoints: List[Int]): Int = lowestPoints.map(_ + 1).sum
}

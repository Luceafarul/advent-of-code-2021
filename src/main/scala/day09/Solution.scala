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

--- Part Two ---

Next, you need to find the largest basins so you know what areas are most important to avoid.

A basin is all locations that eventually flow downward to a single low point.
Therefore, every low point has a basin, although some basins are very small.
Locations of height 9 do not count as being in any basin,
and all other locations will always be part of exactly one basin.

The size of a basin is the number of locations within the basin, including the low point.
The example above has four basins.

The top-left basin, size 3:
[21]99943210
[3]987894921
9856789892
8767896789
9899965678

The top-right basin, size 9:
21999[43210]
398789[4]9[21]
985678989[2]
8767896789
9899965678

The middle basin, size 14:
2199943210
39[878]94921
9[85678]9892
[87678]96789
9[8]99965678

The bottom-right basin, size 9:
2199943210
3987894921
9856789[8]92
876789[678]9
98999[65678]

Find the three largest basins and multiply their sizes together.
In the above example, this is 9 * 14 * 9 = 1134.

What do you get if you multiply together the sizes of the three largest basins?
 */
object Solution {
  type Points = List[List[Int]]
  final case class Heightmap(coordinates: List[Coordinate]) {
    def notChecked(): List[Coordinate] = coordinates.filterNot(_.checked)
  }

  object Heightmap {
    def ofPoints(points: Points): Heightmap = Heightmap {
      (for {
        p <- points
        x <- points.indices
        y <- p.indices
      } yield Coordinate(x, y, points(x)(y))).distinct
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

  def scanForLowestPoints(input: Heightmap): Set[Coordinate] = {
    @tailrec
    def checkAdjacent(
        heightmap: Heightmap,
        coordinate: Coordinate,
        acc: Set[Coordinate],
        coordinatesToCheck: Set[Coordinate] = Set.empty
    ): (Heightmap, Set[Coordinate]) = {
      val adjacent = heightmap.coordinates.filter { c =>
        (c.x == coordinate.x - 1 && c.y == coordinate.y) || // up
        (c.x == coordinate.x + 1 && c.y == coordinate.y) || // down
        (c.x == coordinate.x && c.y == coordinate.y - 1) || // left
        (c.x == coordinate.x && c.y == coordinate.y + 1) // right
      }

      val total = adjacent :+ coordinate
      val min = total.minBy(_.value)
      val checked = total.filter(_ != min).map(_.markChecked)

      val newHeightmap = Heightmap(heightmap.coordinates.filterNot {
        total.contains
      } ++ total.map(_.markChecked))

      val res = (coordinatesToCheck ++ checked + min)
        .filter(_.value <= min.value)
        .filter(_.checked == false)
        .diff(acc)

      if (min.checked || res.isEmpty) (newHeightmap, acc)
      else if (min == coordinate) (newHeightmap, acc + min.markChecked)
      else checkAdjacent(newHeightmap, res.head, acc, res.tail)
    }

    @tailrec
    def loop(input: Heightmap, acc: Set[Coordinate]): Set[Coordinate] = {
      if (input.notChecked().isEmpty) acc
      else {
        val coordinateToCheck = input.notChecked().head
        val (heightmap, mins) = checkAdjacent(input, coordinateToCheck, acc)
        loop(heightmap, mins)
      }
    }

    loop(input, Set.empty)
  }

  def scanForBasins(
      heightmap: Heightmap,
      lowestPoints: Set[Coordinate]
  ): Map[Coordinate, Set[Coordinate]] = {
    @tailrec
    def checkBasin(
        coordinate: Coordinate,
        coordinatesToCheck: Set[Coordinate] = Set.empty,
        basin: Set[Coordinate] = Set.empty
    ): Set[Coordinate] = {
      val adjacent = heightmap.coordinates.filter { c =>
        (c.x == coordinate.x - 1 && c.y == coordinate.y) || // up
        (c.x == coordinate.x + 1 && c.y == coordinate.y) || // down
        (c.x == coordinate.x && c.y == coordinate.y - 1) || // left
        (c.x == coordinate.x && c.y == coordinate.y + 1) || // right
        (c.x == coordinate.x && c.y == coordinate.y) // current
      }

      val basinPoints = adjacent
        .filterNot(_.value == 9)
        .filterNot(basin.contains)
        .toSet

      val toCheck = coordinatesToCheck ++ basinPoints

      if (toCheck.isEmpty) basin
      else
        checkBasin(
          toCheck.head,
          toCheck.tail,
          basin ++ basinPoints
        )
    }

    @tailrec
    def loop(
        lowestPoints: Set[Coordinate],
        acc: Map[Coordinate, Set[Coordinate]]
    ): Map[Coordinate, Set[Coordinate]] = {
      if (lowestPoints.isEmpty) acc
      else {
        loop(
          lowestPoints.tail,
          acc + (lowestPoints.head -> checkBasin(lowestPoints.head))
        )
      }
    }

    val res = loop(lowestPoints, Map.empty)
    res
  }

  def riskLevelsSum(lowestPoints: List[Int]): Int = lowestPoints.map(_ + 1).sum

  def basinsSizes(
      heightmap: Heightmap,
      lowestPoints: Set[Coordinate]
  ): List[Int] = {
    scanForBasins(heightmap, lowestPoints).values
      .map(_.size)
      .toList
      .sortWith(_ > _)
  }

  def topThreeBasinsSizesProduct(
      heightmap: Heightmap,
      lowestPoints: Set[Coordinate]
  ): Int = basinsSizes(heightmap, lowestPoints).take(3).product
}

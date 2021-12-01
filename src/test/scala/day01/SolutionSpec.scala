package day01

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SolutionSpec extends AnyWordSpec with Matchers {
  "A Solution" should {
    "return count of increases depth measurement" in {
      val measurements = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

      Solution.depthMeasurementIncreasesCounter(measurements) shouldBe 7
    }

    "return count of increases three measurement sliding window" in {
      val measurements = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

      Solution.threeMeasurementSlidingWindowCounter(measurements) shouldBe 5
    }
  }
}

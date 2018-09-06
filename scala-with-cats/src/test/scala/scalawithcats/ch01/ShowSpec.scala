package scalawithcats.ch01

import cats.syntax.show._

import scalawithcats.BaseSpec

class ShowSpec extends BaseSpec {

  "Show" should "be showable" in {
    assert(Cat("Jiji", 5, "black").show === "Jiji is a 5 year-old black cat.")
  }

}

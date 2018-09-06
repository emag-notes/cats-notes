package scalawithcats.ch02

import MonoidInstances._

import scalawithcats.BaseSpec

class MonoidSpec extends BaseSpec {

  "Recursive Implicit Monoid resolution" should "be fine" in {
    val intSetMonoid = Monoid[Set[Int]]
    assert(intSetMonoid.combine(Set(1, 2), Set(2, 3)) === Set(1, 2, 3))
  }
}

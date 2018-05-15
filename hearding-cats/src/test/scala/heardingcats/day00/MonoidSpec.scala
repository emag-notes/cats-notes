package heardingcats.day00

import heardingcats.BaseSpec

class MonoidSpec extends BaseSpec {

  behavior of "Monoid"

  it should "resolve Monoid" in {
    assert(sum(List(1, 2, 3, 4)) === 10)
    assert(sum(List("a", "b", "c")) === "abc")
  }

  it should "use directly" in {
    val multiMonoid: Monoid[Int] = new Monoid[Int] {
      override def mappend(a1: Int, a2: Int): Int = a1 * a2
      override def mzero: Int                     = 1
    }

    assert(sum(List(1, 2, 3, 4))(multiMonoid) === 24)
  }

  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

}

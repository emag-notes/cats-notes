package heardingcats.day00

import heardingcats.BaseSpec

import scala.language.higherKinds

class FoldLeftSpec extends BaseSpec {

  behavior of "FoldLeft"

  it should "" in {
    assert(sum(List(1, 2, 3, 4)) === 10)
    assert(sum(List("a", "b", "c")) === "abc")
  }

  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m  = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
}

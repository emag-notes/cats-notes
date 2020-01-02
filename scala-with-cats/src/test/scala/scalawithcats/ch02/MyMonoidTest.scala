package scalawithcats.ch02

import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class MyMonoidTest extends AnyFlatSpec with Diagrams {

  "MyMonoid" should "satisfy associative law and identity law" in {
    implicit val intMonoid: Monoid[Int] = MonoidInstances.intMonoid

    assert(
      intMonoid.combine(1, intMonoid.combine(2, 3)) ===
        intMonoid.combine(intMonoid.combine(1, 2), 3)
    )

    assert(
      (intMonoid.combine(1, intMonoid.empty) === 1) &&
      (intMonoid.combine(intMonoid.empty, 1) === 1)
    )

    val intSetMonoid: Monoid[Set[Int]] = MonoidInstances.setUnionMonoid[Int]()
    assert(intSetMonoid.combine(Set(1, 2), Set(2, 3)) === Set(1, 2, 3))
  }

}

package scalawithcats.ch02.cats

import cats.{Monoid, Semigroup}
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.semigroup._
import scalawithcats.BaseSpec

class CatsMonoidSpec extends BaseSpec {

  "Monoid from Cats" should "have embed instances and syntax" in {
    val hiThere = "Hi, there"

    assert(Monoid[String].combine("Hi, ", "there") === hiThere)
    assert(Monoid[String].empty === "")

    assert(Semigroup[String].combine("Hi, ", "there") === hiThere)

    assert(Monoid[Int].combine(32, 10) === 42)
    assert(Monoid[Int].empty === 0)

    assert(Monoid[Option[Int]].combine(Option(22), Option(20)) === Option(42))
    assert(Monoid[Option[Int]].empty === None)

    assert(("Hi, " |+| "there" |+| Monoid[String].empty) === hiThere)
    assert((1 |+| 2 |+| Monoid[Int].empty) === 3)
  }
}

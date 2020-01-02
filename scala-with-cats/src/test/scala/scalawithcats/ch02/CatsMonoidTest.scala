package scalawithcats.ch02

import cats.instances.int._
import cats.instances.map._
import cats.instances.option._
import cats.instances.string._
import cats.instances.tuple._
import cats.syntax.semigroup._
import cats.{Monoid, Semigroup}
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class CatsMonoidTest extends AnyFlatSpec with Diagrams {

  "Monoid" should "have combine and empty" in {
    assert(Monoid[String].combine("Hi ", "there") === "Hi there")
    assert(Monoid[String].empty === "")
    assert(Semigroup[String].combine("Hi ", "there") === "Hi there")
    assert(Monoid[Int].combine(32, 10) === 42)
    assert(Monoid[Option[Int]].combine(Option(32), Option(10)) === Some(42))

    assert(("Hi " |+| "there" |+| Monoid[String].empty) === "Hi there")
    assert((1 |+| 2 |+| Monoid[Int].empty) === 3)

    assert(SuperAdder.add(List(1, 2, 3)) === 6)
    assert(SuperAdder.add(List(Option(1), Option(2), Option(3))) === Some(6))

    import CatsMonoidInstances._
    assert(
      SuperAdder.add(List(Order(totalCost = 100d, quantity = 1d), Order(totalCost = 200d, quantity = 2d))) ===
        Order(
          totalCost = 300d,
          quantity = 3d
        )
    )

    val m1 = Map("a" -> 1, "b" -> 2)
    val m2 = Map("b" -> 3, "d" -> 4)
    val m3 = Map("a" -> 1, "b" -> 5, "d" -> 4)
    assert((m1 |+| m2) === m3)

    val t1 = ("hello", 123)
    val t2 = ("world", 321)
    val t3 = ("helloworld", 444)
    assert((t1 |+| t2) === t3)
  }

}

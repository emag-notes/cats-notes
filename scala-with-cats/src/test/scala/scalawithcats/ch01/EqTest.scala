package scalawithcats.ch01

import java.util.Date
import java.util.concurrent.TimeUnit

import cats.Eq
import cats.instances.int._
import cats.instances.long._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.eq._
import cats.syntax.option._
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class EqTest extends AnyFlatSpec with Diagrams {

  "Eq" should "check values including types" in {
    val intEq = Eq[Int]
    assert(intEq.eqv(123, 123))
//    assert(intEq.eqv(123, "123")) // type mismatch
    assert(123 eqv 123)

    assert(1.some neqv none[Int])

    val x = new Date()
    TimeUnit.MILLISECONDS.sleep(100)
    val y = new Date()

    assert(x eqv x)
    assert(x neqv y)

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")

    assert(cat1 neqv cat2)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    assert(optionCat1 neqv optionCat2)
  }

  implicit val dateEq: Eq[Date] = Eq.instance[Date] { (date1, date2) =>
    date1.getTime eqv date2.getTime
  }

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name eqv cat2.name) &&
    (cat1.age eqv cat2.age) &&
    (cat1.color eqv cat2.color)
  }

}

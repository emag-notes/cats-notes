package scalawithcats.ch01

import java.util.Date

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class CatsTest extends AnyFlatSpec with Diagrams {

  "Show" should "provide a mechanism for producing string representation" in {
    val showInt    = Show.apply[Int]
    val showString = Show.apply[String]

    assert(showInt.show(123) === "123")
    assert(showString.show("abc") === "abc")

    assert(123.show === "123")
    assert("abc".show === "abc")

    assert(new Date(1567004775393L).show === "1567004775393ms since the epoch.")

    assert(Cat("Tom", 10, "Calico").show === "Tom is a 10 year-old Calico cat.")
  }

  implicit val dateShow: Show[Date] =
    Show.show(date => s"${date.getTime}ms since the epoch.")

  implicit val catShow: Show[Cat] =
    Show.show(cat => {
      val name  = cat.name.show
      val age   = cat.age.show
      val color = cat.color.show
      s"$name is a $age year-old $color cat."
    })

}

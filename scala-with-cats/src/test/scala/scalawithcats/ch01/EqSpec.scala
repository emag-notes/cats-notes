package scalawithcats.ch01

import cats.syntax.eq._
import scalawithcats.BaseSpec
import cats.instances.option._

class EqSpec extends BaseSpec {

  "Eq" should "be able to check two instances with type level" in {
    val cat1 = Cat("Jiji", 5, "black")
    val cat2 = Cat("Gin", 5, "silver")

    assert(Cat.catEqual.eqv(cat1, cat1)) // cat1 === cat1
    assert(cat1 =!= cat2)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option(cat2)

//    cat1 =!= optionCat1 compile error

    assert(optionCat1 =!= optionCat2)
  }

}

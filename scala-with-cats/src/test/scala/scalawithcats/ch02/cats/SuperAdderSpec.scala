package scalawithcats.ch02.cats

import cats.instances.int._
import cats.instances.string._
import cats.instances.option._

import scalawithcats.BaseSpec

class SuperAdderSpec extends BaseSpec {

  "SuperAdder" should "be supper adder...!" in {
    assert(SuperAdder.add(List(1, 2, 3)) === 6)
    assert(SuperAdder.add(List("a", "b", "c")) === "abc")
    assert(SuperAdder.add(List(Some(1), None, Some(2), None, Some(3))) === Some(6))

    assert(SuperAdder.add(List(Order(1D, 2D), Order(2D, 3D))) === Order(3D, 5D))
  }
}

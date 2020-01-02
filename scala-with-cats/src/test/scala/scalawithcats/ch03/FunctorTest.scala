package scalawithcats.ch03

import cats.Functor
import cats.instances.function._
import cats.instances.list._
import cats.instances.option._
import cats.syntax.functor._
import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class FunctorTest extends AnyFlatSpec with Diagrams {

  "Functor" should "have map function" in {
    val func1: Int => Double    = (x: Int) => x.toDouble
    val func2: Double => Double = (y: Double) => y * 2

    assert((func1 map func2).isInstanceOf[Int => Double])

    assert((func1 map func2)(1) === 2.0d)

    assert((func1 andThen func2)(1) === 2.0d)

    assert(func2(func1(1)) === 2.0d)

    val func3: Int => String =
      ((x: Int) => x.toDouble)
        .map(_ + 1)
        .map(_ * 2)
        .map(x => s"$x!")

    assert(func3(123) === "248.0!")

    val list1 = List(1, 2, 3)
    val list2 = Functor[List].map(list1)(_ * 2)
    assert(list2 === List(2, 4, 6))

    val option1 = Option(123)
    val option2 = Functor[Option].map(option1)(_.toString)
    assert(option2 === Some("123"))

    val func4: Int => Int                      = (x: Int) => x + 1
    val liftedFunc: Option[Int] => Option[Int] = Functor[Option].lift(func4)
    assert(liftedFunc(Option(1)) === Some(2))

    assert(doMath(Option(1)) === Some(3))

    import FunctorInstances._
    assert((Tree.leaf(100).map(_ * 2)) === Leaf(200))
    assert(
      Tree
        .branch(
          Tree.leaf(10),
          Tree.branch(
            Tree.leaf(20),
            Tree.leaf(30)
          )
        )
        .map(_ * 2)
        === Branch(Leaf(20), Branch(Leaf(40), Leaf(60)))
    )
  }

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

}

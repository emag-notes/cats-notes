package scalawithcats.ch04.usecats

import scala.language.higherKinds
import cats.{Id, Monad}
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.instances.option._
import scalawithcats.BaseSpec

class IdentityMonadSpec extends BaseSpec {

  "Cats" should "have Identity Monad" in {
    assert(sumSquare(Option(1), Option(2)) === Option(5))

    assert(sumSquare(1: Id[Int], 2: Id[Int]) === 5)

    val a = Monad[Id].pure(3)
    assert(a === 3)

    assert(Monad[Id].flatMap(a)(_ + 1) === 4)
  }

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x * x + y * y
}

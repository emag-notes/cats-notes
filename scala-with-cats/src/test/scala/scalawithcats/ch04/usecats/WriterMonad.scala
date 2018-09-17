package scalawithcats.ch04.usecats

import java.util.concurrent.TimeUnit

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._
import scalawithcats.BaseSpec

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class WriterMonad extends BaseSpec {

  type Logged[A] = Writer[Vector[String], A]

  "WriterMonad" should "be useful for logging operations in multi-threaded environments" in {
    val fs: Future[Vector[(Vector[String], Int)]] = Future.sequence(
      Vector(
        Future(factorial(3).run),
        Future(factorial(5).run)
      )
    )

    val Vector((logA, ansA), (logB, ansB)) = Await.result(fs, 5.seconds)
    assert(logA === Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6"))
    assert(ansA === 6)

    assert(logB === Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6", "fact 4 24", "fact 5 120"))
    assert(ansB === 120)
  }

  def slowly[A](body: => A): A =
    try body
    finally TimeUnit.MILLISECONDS.sleep(500)

  def factorial(n: Int): Logged[Int] = {
    for {
      ans <- if (n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }
}

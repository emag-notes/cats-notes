package scalawithcats.ch04.usecats

import cats.MonadError
import cats.instances.either._
import cats.syntax.applicative._
import cats.syntax.applicativeError._
import cats.syntax.monadError._
import cats.instances.try_._
import scalawithcats.BaseSpec

import scala.util.{Failure, Try}

class MonadErrorSpec extends BaseSpec {

  type ErrorOr[A] = Either[String, A]

  behavior of "MonadError"

  it should "abstract Either-like types" in {
    val monadError = MonadError[ErrorOr, String]

    val success: ErrorOr[Int] = monadError.pure(42)
    assert(success === Right(42))

    val failure: ErrorOr[Nothing] = monadError.raiseError("Badness")
    assert(failure === Left("Badness"))

    val handled: ErrorOr[ErrorOr[String]] = monadError.handleError(failure) {
      case "Badness" => monadError.pure("It's ok")
      case _         => monadError.raiseError("it's not ok")
    }
    assert(handled === Right(Right("It's ok")))

    val ensured: ErrorOr[Int] = monadError.ensure(success)("Number too low!")(_ > 1000)
    assert(ensured === Left("Number too low!"))
  }

  it should "have its syntax" in {
    val success: ErrorOr[Int] = 42.pure[ErrorOr]
    assert(success === Right(42))

    val failure: ErrorOr[Int] = "Badness".raiseError[ErrorOr, Int]
    assert(failure === Left("Badness"))

    val ensured: ErrorOr[Int] = success.ensure("Number too low!")(_ > 1000)
    assert(ensured === Left("Number too low!"))
  }

  it should "have its instances" in {
    val exn: Throwable = new RuntimeException("It's all gone wrong")

    val error: Try[Int] = exn.raiseError[Try, Int]
    assert(error === Failure(exn))
  }

}

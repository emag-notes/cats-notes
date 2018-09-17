package scalawithcats.ch04.usecats

import cats.syntax.either._
import scalawithcats.BaseSpec

class EitherMonadSpec extends BaseSpec {

  "Cats either syntax" should "return Either instead of Left/Right and so on" in {
    val rightApply: Either[Nothing, Int] = Right(3)
    val asRight: Either[String, Int]     = 3.asRight[String]

    assert(countPositive(List(1, 2, 3, -1, 99)) == Left("Negative. Stopping!"))

    assert(Either.fromTry(scala.util.Try("foo".toInt)).isLeft)
    assert(Either.fromOption[String, Int](None, "Badness").isLeft)
  }

  def countPositive(nums: List[Int]) =
//    nums.foldLeft(Right(0)) { (accumulator, num) =>
    nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
      if (num > 0) {
        accumulator.map(_ + 1)
      } else {
        Left("Negative. Stopping!")
      }
    }
}

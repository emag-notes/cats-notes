package scalawithcats.ch04.usecats

import cats.data.State
import scalawithcats.BaseSpec

class StateMonadSpec extends BaseSpec {

  behavior of "StateMonad"

  it should "hold state and result" in {
    val a: State[Int, String] = State[Int, String] { state =>
      (state, s"The state is $state")
    }

    val (state, result) = a.run(10).value
    assert(state === 10)
    assert(result === "The state is 10")

    val stateOnly = a.runS(10).value
    assert(stateOnly === 10)

    val resultOnly = a.runA(10).value
    assert(resultOnly === "The state is 10")
  }

  it should "be composed and transformed" in {
    val step1 = State[Int, String] { num =>
      val ans = num + 1
      (ans, s"Result of step1: $ans")
    }

    val step2 = State[Int, String] { num =>
      val ans = num * 2
      (ans, s"Result of step2: $ans")
    }

    val both: State[Int, (String, String)] = for {
      a <- step1
      b <- step2
    } yield (a, b)

    val (state, result) = both.run(20).value
    assert(state === 42)
    assert(result === ("Result of step1: 21", "Result of step2: 42"))
  }

  "Cats" should "provide some constructors" in {
    import State._
    val program: State[Int, (Int, Int, Int)] = for {
      a <- get[Int]
      _ <- set[Int](a + 1)
      b <- get[Int]
      _ <- modify[Int](_ + 1)
      c <- inspect[Int, Int](_ * 1000)
    } yield (a, b, c)

    val (state, result) = program.run(1).value
    assert(state === 3)
    assert(result === (1, 2, 3000))
  }

  "Calc" should "be able to calc" in {
    import Calc._
    assert(evalOne("42").runA(Nil).value === 42)

    val program = for {
      _   <- evalOne("1")
      _   <- evalOne("2")
      ans <- evalOne("+")
    } yield ans

    assert(program.runA(Nil).value === 3)

    assert(evalAll(List("1", "2", "+", "3", "*")).runA(Nil).value === 9)

    assert((for {
      _   <- evalAll(List("1", "2", "+"))
      _   <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans).runA(Nil).value === 21)

    assert(evalInput("1 2 + 3 4 + *") === 21)
  }
}

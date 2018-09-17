package scalawithcats.ch04.usecats

import cats.Eval
import scalawithcats.BaseSpec

class EvalSpec extends BaseSpec {

  behavior of "Eval"

  it should "has three subtypes" in {
    // like val
    val now = Eval.now(math.random + 1000)
    // like lazy val
    val later = Eval.later(math.random + 2000)
    // like def
    val always = Eval.always(math.random + 3000)

    println(s"now: $now")
    println(s"now.value 1: ${now.value}")
    println(s"now.value 2: ${now.value}")
    println(s"later: $later")
    println(s"later.value 1: ${later.value}")
    println(s"later.value 2: ${later.value}")
    println(s"always: $always")
    println(s"always.value 1: ${always.value}")
    println(s"always.value 2: ${always.value}")
  }

  it should "have map/flatMap" in {
    val greeting: Eval[String] = Eval
      .always { println("Step 1"); "Hello" }
      .map { str =>
        println("Step 2"); s"$str world"
      }

    println(s"greeting: $greeting")
    assert(greeting.value === "Hello world")

    val ans = for {
      a <- Eval.now { println("Calculating A"); 40 }
      b <- Eval.always { println("Calculating B"); 2 }
    } yield {
      println("Adding A and B")
      a + b
    }

    println(s"ans: $ans")
    assert(ans.value === 42)

    val saying = Eval
      .always { println("Step 1"); "The cat" }
      .map { str =>
        println("Step 2"); s"$str sat on"
      }
      .memoize
      .map { str =>
        println("Step 3"); s"$str the mat"
      }

    println(s"saying: $saying")
    assert(saying.value === "The cat sat on the mat")
    assert(saying.value === "The cat sat on the mat")
  }

  it should "have map/flatMap which are trampolined" in {
    // easy to make this stack overflow like `factorial(50000)`
//    def factorial(n: BigInt): BigInt = if (n == 1) n else n * factorial(n - 1)

    def factorial(n: BigInt): Eval[BigInt] =
      if (n == 1) {
        Eval.now(n)
      } else {
        Eval.defer(factorial(n - 1).map(_ * n))
      }
    val result = factorial(10).value
    assert(result === 3628800)

//    def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = as match {
//      case head :: tail => fn(head, foldRight(tail, acc)(fn))
//      case Nil          => acc
//    }

    def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
      as match {
        case head :: tail => Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
        case Nil          => acc
      }

    def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
      foldRightEval(as, Eval.now(acc)) { (a, b) =>
        b.map(fn(a, _))
      }.value

    val sum = foldRight((1 to 10000).toList, 0)(_ + _)
    assert(sum === 50005000)

  }

}

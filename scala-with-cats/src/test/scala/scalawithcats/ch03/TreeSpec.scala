package scalawithcats.ch03

import cats.syntax.functor._
import cats.syntax.flatMap._

import Tree._

import scalawithcats.BaseSpec

class TreeSpec extends BaseSpec {

  "Tree" should "have map" in {
    assert(leaf(100).map(_ * 2) === Leaf(200))

    assert(
      branch(leaf(10), leaf(20)).map(_ * 2)
        === Branch(Leaf(20), Leaf(40))
    )

    assert(
      branch(
        branch(leaf(10), leaf(20)),
        branch(
          leaf(30),
          branch(leaf(40), leaf(50))
        )
      ).map(_ * 2)

        ===

          Branch(
            Branch(
              Leaf(20),
              Leaf(40)
            ),
            Branch(
              Leaf(60),
              Branch(Leaf(80), Leaf(100))
            )
          )
    )
  }

  "Tree" should "be monad" in {
    val result: Tree[Int] = for {
      a <- branch(leaf(100), leaf(200))
      b <- branch(leaf(a - 10), leaf(a + 10))
      c <- branch(leaf(b - 1), leaf(b + 1))
    } yield c

    println(
      result ===
        Branch(
          Branch(Branch(Leaf(89), Leaf(91)), Branch(Leaf(109), Leaf(111))),
          Branch(Branch(Leaf(189), Leaf(191)), Branch(Leaf(209), Leaf(211)))
        )
    )
  }
}

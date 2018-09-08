package scalawithcats.ch03

import cats.syntax.functor._

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
}

package scalawithcats.ch03.usecats

import cats.Monoid
import cats.instances.string._
import cats.syntax.invariant._
import cats.syntax.semigroup._
import scalawithcats.BaseSpec

class InvariantSpec extends BaseSpec {

  "Cats" should "provide invariant implementation" in {
    implicit val symbolMonoid: Monoid[Symbol] = Monoid[String].imap(Symbol.apply)(_.name)

    assert(Monoid[Symbol].empty === Symbol.apply(""))
    assert(('a |+| 'few |+| 'words) === 'afewwords)
  }

}

package scalawithcats.ch03

import cats.{Contravariant, Show}
import cats.instances.string._
import cats.syntax.contravariant._
import org.scalatest.flatspec.AnyFlatSpec
import MonoidInstances._
import cats.Monoid
import cats.syntax.semigroup._

class CatsVariantTest extends AnyFlatSpec {

  "Contravariant" should "have contramap" in {
    val showString = Show[String]

    val showSymbol = Contravariant[Show]
      .contramap(showString)((sym: Symbol) => s"'${sym.name}")

    assert(showSymbol.show(Symbol("dave")) === "'dave")

    assert(showString.contramap[Symbol](sym => s"'${sym.name}").show(Symbol("dave")) === "'dave")
  }

  "Invariant" should "have imap" in {
    assert(Monoid[Symbol].empty === Symbol(""))
    assert((Symbol("a") |+| Symbol("few") |+| Symbol("words")) === Symbol("afewwords"))
  }

}

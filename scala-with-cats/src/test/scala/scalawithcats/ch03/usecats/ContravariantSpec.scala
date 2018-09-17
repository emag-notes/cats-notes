package scalawithcats.ch03.usecats

import cats.{Contravariant, Show}
import cats.syntax.contravariant._
import cats.instances.string._
import scalawithcats.BaseSpec

class ContravariantSpec extends BaseSpec {

  "Cats" should "provide contravariant implementation" in {
    val showString = Show[String]
    val showSymbol = Contravariant[Show].contramap(showString)((sym: Symbol) => s"'${sym.name}")

    assert(showSymbol.show('dave) === "'dave")

    assert(
      showString.contramap[Symbol](sym => s"'${sym.name}").show('dave)
        ===
          "'dave"
    )
  }

}

package scalawithcats.ch03

import cats.Monoid
import cats.instances.string._
import cats.syntax.invariant._

object MonoidInstances {
  implicit val symbolMonoid: Monoid[Symbol] = Monoid[String].imap(Symbol.apply)(_.name)
}

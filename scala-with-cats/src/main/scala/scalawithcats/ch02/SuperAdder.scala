package scalawithcats.ch02

import cats.Monoid
import cats.syntax.semigroup._

object SuperAdder {

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

}

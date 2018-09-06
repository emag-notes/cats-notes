package scalawithcats.ch02.cats

import cats.Monoid
import cats.syntax.semigroup._

object SuperAdder {

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)

  // Context Bound
//  def add[A: Monoid](items: List[A]): A =
//    items.foldLeft(Monoid[A].empty)(_ |+| _)

}

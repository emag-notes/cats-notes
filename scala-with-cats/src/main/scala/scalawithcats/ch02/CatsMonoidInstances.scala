package scalawithcats.ch02

import cats.Monoid

object CatsMonoidInstances {

  implicit val OrderMonoid: Monoid[Order] = new Monoid[Order] {
    override def combine(x: Order, y: Order): Order =
      Order(totalCost = x.totalCost + y.totalCost, quantity = x.quantity + y.quantity)
    override def empty: Order = Order(totalCost = 0, quantity = 0)
  }

}

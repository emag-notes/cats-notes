package scalawithcats.ch02.cats

import cats.Monoid

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    override def combine(o1: Order, o2: Order): Order =
      Order(
        totalCost = o1.totalCost + o2.totalCost,
        quantity = o1.quantity + o2.quantity
      )

    override def empty: Order = Order(0, 0)
  }
}

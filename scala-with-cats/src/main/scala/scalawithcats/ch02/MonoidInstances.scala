package scalawithcats.ch02

object MonoidInstances {

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def combine(x: Int, y: Int): Int = x + y
    override def empty: Int                   = 0
  }

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean                           = true
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean                           = false
  }

  implicit val booleanXorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
    override def empty: Boolean                           = false
  }

  implicit val booleanXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x && !y)
    override def empty: Boolean                           = true
  }

  implicit def setUnionMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    override def empty: Set[A]                         = Set.empty
  }

  implicit def symDiffMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = (x diff y) union (y diff x)
    override def empty: Set[A]                         = Set.empty
  }

}

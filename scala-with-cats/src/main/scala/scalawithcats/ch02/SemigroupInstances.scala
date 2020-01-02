package scalawithcats.ch02

object SemigroupInstances {

  implicit def setIntersectionSemigroup[A](): Semigroup[Set[A]] =
    (x: Set[A], y: Set[A]) => x intersect y

}

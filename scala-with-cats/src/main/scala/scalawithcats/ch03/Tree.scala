package scalawithcats.ch03

import cats.{Functor, Monad}

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

  def leaf[A](value: A): Tree[A] = Leaf(value)

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
      tree match {
        case Branch(left, right) => Branch(map(left)(f), map(right)(f))
        case Leaf(value)         => Leaf(f(value))
      }
  }

  implicit val treeMonad: Monad[Tree] = new Monad[Tree] {
    override def pure[A](value: A): Tree[A] = Leaf(value)

    override def flatMap[A, B](tree: Tree[A])(func: A => Tree[B]): Tree[B] =
      tree match {
        case Branch(left, right) => Branch(flatMap(left)(func), flatMap(right)(func))
        case Leaf(value)         => func(value)
      }

    override def tailRecM[A, B](arg: A)(func: A => Tree[Either[A, B]]): Tree[B] =
      func(arg) match {
        case Branch(left, right) =>
          Branch(
            flatMap(left) {
              case Left(l)  => tailRecM(l)(func)
              case Right(l) => pure(l)
            },
            flatMap(right) {
              case Left(r)  => tailRecM(r)(func)
              case Right(r) => pure(r)
            },
          )

        case Leaf(Left(value)) => tailRecM(value)(func)

        case Leaf(Right(value)) => Leaf(value)
      }
  }
}

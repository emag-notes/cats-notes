package heardingcats.day00.method_injection.simulacrum

import simulacrum._

import scala.language.implicitConversions

@typeclass trait Monoid[A] {
  @op("|+|") def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid {
  // ops は自動生成される
  val syntax = ops

  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 + a2
    override def mzero: Int                     = 0
  }

  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    override def mappend(a1: String, a2: String): String = a1 + a2
    override def mzero: String                           = ""
  }
}

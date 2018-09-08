package scalawithcats.ch01

object PrintableInstances {

  implicit val stringPrintable: Printable[String] = (value: String) => "\"" + value + "\""

  implicit val intPrintable: Printable[Int] = (value: Int) => value.toString

  implicit val booleanPrintable: Printable[Boolean] = (value: Boolean) => if (value) "yes" else "no"

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)
}

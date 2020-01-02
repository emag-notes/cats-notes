package scalawithcats.ch03

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = "\"" + value + "\""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    override def format(value: Boolean): String = if (value) "yes" else "no"
  }

  implicit def boxPrintable[A](implicit printable: Printable[A]): Printable[Box[A]] = printable.contramap(_.value)

}

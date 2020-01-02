package scalawithcats.ch03

object CodecInstances {

  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value
    override def decode(value: String): String = value
  }

  implicit val intCodec: Codec[Int]         = stringCodec.imap(dec = _.toInt, enc = _.toString)
  implicit val doubleCodec: Codec[Double]   = stringCodec.imap(dec = _.toDouble, enc = _.toString)
  implicit val booleanCodec: Codec[Boolean] = stringCodec.imap(dec = _.toBoolean, enc = _.toString)

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] = c.imap(
    dec = Box(_),
    enc = _.value
  )
}

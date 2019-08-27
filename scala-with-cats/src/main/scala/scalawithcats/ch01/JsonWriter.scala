package scalawithcats.ch01

// type class
trait JsonWriter[A] {
  def write(value: A): Json
}

object JsonWriter {
  // type instances
  implicit val stringWriter: JsonWriter[String] = (value: String) => JsString(value)

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = {
    case Some(aValue) => writer.write(aValue)
    case None         => JsNull
  }
}

package scalawithcats.ch01

object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
    override def write(person: Person): Json =
      JsObject(
        Map(
          "name"  -> JsString(person.name),
          "email" -> JsString(person.email)
        )
      )
  }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def write(option: Option[A]): Json = option match {
      case Some(aValue) => writer.write(aValue)
      case None         => JsNull
    }
  }
}

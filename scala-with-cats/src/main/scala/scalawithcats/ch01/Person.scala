package scalawithcats.ch01

case class Person(name: String, email: String)

object Person {
  // type instances
  implicit val personWriter: JsonWriter[Person] = (value: Person) =>
    JsObject(
      Map(
        "name"  -> JsString(value.name),
        "email" -> JsString(value.email)
      )
    )
}

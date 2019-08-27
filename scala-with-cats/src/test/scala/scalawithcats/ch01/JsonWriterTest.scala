package scalawithcats.ch01

import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class JsonWriterTest extends AnyFlatSpec with Diagrams {

  "Json.toJson" should "serialize to json" in {
    assert(Json.toJson(dave) === daveJson)
  }

  "JsonSyntax" should "serialize to json" in {
    import JsonSyntax._

    assert(dave.toJson === daveJson)
  }

  "writer via implicitly" should "serialize to json" in {
    val writer = implicitly[JsonWriter[Person]]
    assert(writer.write(dave) === daveJson)
  }

  "option" should "be supported" in {
    assert(Json.toJson(dave) === daveJson)
    assert(Json.toJson(Option.empty[Person]) === JsNull)
  }

  private lazy val dave = Person("Dave", "dave@example.com")
  private lazy val daveJson = JsObject(
    Map(
      "name"  -> JsString("Dave"),
      "email" -> JsString("dave@example.com")
    )
  )

}

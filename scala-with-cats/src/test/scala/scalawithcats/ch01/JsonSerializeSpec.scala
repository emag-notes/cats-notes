package scalawithcats.ch01

import JsonWriterInstances._
import JsonSyntax._

import scalawithcats.BaseSpec

class JsonSerializeSpec extends BaseSpec {

  "Json.toJson(Interface Objects)" should "serialize an object to json" in {
    val name  = "Dave"
    val email = "dave@example.com"

    assert(
      Json.toJson(Person(name, email)) ===
        JsObject(Map("name" -> JsString(name), "email" -> JsString(email)))
    )
  }

  "JsonSyntax(Interface Syntax)" should "serialize an object to Json" in {
    val name  = "Dave"
    val email = "dave@example.com"

    assert(
      Person(name, email).toJson ===
        JsObject(Map("name" -> JsString(name), "email" -> JsString(email)))
    )
  }

  "Recursive Instance" should "be no problem!" in {
    assert(Json.toJson(Option("A string")) === JsString("A string"))
  }

}

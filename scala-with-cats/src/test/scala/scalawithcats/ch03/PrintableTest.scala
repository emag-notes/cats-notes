package scalawithcats.ch03

import org.scalatest.flatspec.AnyFlatSpec
import PrintableInstances._

class PrintableTest extends AnyFlatSpec {

  "Printable" should "enable some types to print" in {
    assert(Printable.format("hello") === "\"hello\"")
    assert(Printable.format(true) === "yes")
    assert(Printable.format(Box("hello world")) === "\"hello world\"")
    assert(Printable.format(Box(false)) === "no")
  }
}

package scalawithcats.ch01

import scalawithcats.BaseSpec
import scalawithcats.ch01.PrintableInstances._
import scalawithcats.ch01.PrintableSyntax._

class PrintableSpec extends BaseSpec {

  "Printable" should "be printable" in {
    assert(Printable.format("A") === "A")
    assert(Printable.format(1) === "1")
    Printable.print("print B")

    val cat      = Cat("Jiji", 5, "black")
    val expected = "Jiji is a 5 year-old black cat."
    assert(Printable.format(cat) === expected)
    Printable.print(cat)

    assert(cat.format === expected)
    cat.print
  }
}

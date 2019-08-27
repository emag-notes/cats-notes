package scalawithcats.ch01

import org.scalatest.diagrams.Diagrams
import org.scalatest.flatspec.AnyFlatSpec

class PrintableTest extends AnyFlatSpec with Diagrams {

  "Printable" should "be able to print" in {
    import PrintableInstances._

    assert(Printable.format(1) === "1")

    val cat = Cat("Tom", 10, "Calico")
    assert(Printable.format(cat) === "Tom is a 10 year-old Calico cat.")

    import PrintableSyntax._
    assert(cat.format === "Tom is a 10 year-old Calico cat.")
  }

}

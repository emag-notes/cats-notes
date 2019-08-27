package scalawithcats.ch01

final case class Cat(name: String, age: Int, color: String)

object Cat {

  import PrintableInstances._

  implicit val catPrintable: Printable[Cat] = (cat: Cat) => {
    val name  = Printable.format(cat.name)
    val age   = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }

}

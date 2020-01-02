package scalawithcats.ch03

import org.scalatest.flatspec.AnyFlatSpec
import CodecInstances._

class CodecTest extends AnyFlatSpec {

  "Codec" should "codec values" in {
    assert(Codec.encode(123.4d) === "123.4")
    assert(Codec.decode[Double]("123.4") === 123.4d)
    assert(Codec.encode(Box(123.4d)) === "123.4")
    assert(Codec.decode[Box[Double]]("123.4") === Box(123.4d))
  }
}

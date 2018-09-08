package scalawithcats.ch03

import CodecInstances._
import scalawithcats.BaseSpec
import scalawithcats.ch01.Box

class CodecSpec extends BaseSpec {

  "Codec" should "be able to codec" in {
    assert(Codec.encode(123.4) === "123.4")
    assert(Codec.decode[Double]("123.4") === 123.4)
    assert(Codec.encode(Box(123.4)) === "123.4")
    assert(Codec.decode[Box[Double]]("123.4") === Box(123.4))
  }

}

package heardingcats.day00.method_injection.cats

import cats.implicits._
import heardingcats.BaseSpec

class CatsSpec extends BaseSpec {

  behavior of "Cats"

  it should "resolve injected methods" in {
    assert(1.some.contains(1))
    assert(1.some.orEmpty == 1)
  }

}

package heardingcats.day00.method_injection.simulacrum

import heardingcats.BaseSpec
import Monoid.syntax._

class MonoidSpec extends BaseSpec {

  behavior of "Monoid"

  it should "resolve injected methods" in {
    assert((3 |+| 4) === 7)
    assert(("a" |+| "b") === "ab")
  }
}

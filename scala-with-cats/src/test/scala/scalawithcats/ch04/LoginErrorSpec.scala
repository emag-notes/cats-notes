package scalawithcats.ch04

import cats.syntax.either._
import scalawithcats.BaseSpec
import scalawithcats.ch04.LoginError.{LoginResult, UserNotFound}

class LoginErrorSpec extends BaseSpec {

  "LoginError" should "be foldable" in {

    val result1: LoginResult = User("dave", "passw0rd").asRight
    assert(result1.fold(LoginError.handleError, _.username) === "dave")

    val result2: LoginResult = UserNotFound("dave").asLeft
    result2.fold(LoginError.handleError, println)
  }
}

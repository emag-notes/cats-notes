package scalawithcats.ch04.usecats

import cats.Id
import cats.data.Reader
import cats.syntax.applicative._
import scalawithcats.BaseSpec

class ReaderMonadSpec extends BaseSpec {

  behavior of "ReaderMonad"

  it should "be able to compose Readers" in {
    val garfield = Cat("Garfield", "lasagne")

    val catName: Reader[Cat, String] = Reader(cat => cat.name)
    assert(catName.run(garfield) === "Garfield")

    val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello $name")
    assert(greetKitty.run(garfield) === "Hello Garfield")

    val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

    val greetAndFeed: Reader[Cat, String] =
      for {
        greet <- greetKitty
        feed  <- feedKitty
      } yield s"$greet. $feed."

    assert(greetAndFeed.run(garfield) === "Hello Garfield. Have a nice bowl of lasagne.")

    assert(greetAndFeed.run(Cat("Heathcliff", "junk food")) === "Hello Heathcliff. Have a nice bowl of junk food.")

    val users = Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    )

    val passwords = Map(
      "dade"  -> "zeracool",
      "kate"  -> "acidburn",
      "margo" -> "secret"
    )

    val db = Db(users, passwords)

    val loginSuccess: Id[Boolean] = Db.checkLogin(1, "zeracool").run(db)
    assert(loginSuccess === true)

    val loginError: Id[Boolean] = Db.checkLogin(4, "davinci").run(db)
    assert(loginError === false)
  }

}

case class Cat(name: String, favoriteFood: String)

case class Db(usernames: Map[Int, String], passwords: Map[String, String])

object Db {
  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      maybeUsername <- findUsername(userId)
      passwordOk <- maybeUsername
        .map { username =>
          checkPassword(username, password)
        }
        .getOrElse {
          // error ambiguous implicit values
//          false.pure[DbReader]
          Reader[Db, Boolean](_ => false)
        }
    } yield passwordOk
}

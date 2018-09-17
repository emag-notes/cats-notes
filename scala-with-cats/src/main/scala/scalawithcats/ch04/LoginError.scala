package scalawithcats.ch04

case class User(username: String, password: String)

sealed trait LoginError extends Product with Serializable

object LoginError {

  final case class UserNotFound(username: String) extends LoginError

  final case class PasswordIncorrect(username: String) extends LoginError

  case object UnexpectedError extends LoginError

  type LoginResult = Either[LoginError, User]

  def handleError(error: LoginError): Unit = error match {
    case UserNotFound(u)      => println(s"User not found: $u")
    case PasswordIncorrect(u) => println(s"Password incorrect: $u")
    case UnexpectedError      => println("Unexpected error")
  }
}

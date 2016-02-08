package walfie.photokatsu.util

object StringOps {
  implicit class StringToInt(val s: String) extends AnyVal {
    def toIntOpt(): Option[Int] = try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
}


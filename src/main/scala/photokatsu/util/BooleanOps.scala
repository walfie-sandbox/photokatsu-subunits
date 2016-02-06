package photokatsu.util

object BooleanOps {
  implicit class BooleanToOption(val bool: Boolean) extends AnyVal {
    def option[T](value: => T): Option[T] = if (bool) Some(value) else None
  }
}


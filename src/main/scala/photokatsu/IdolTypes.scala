package photokatsu

sealed trait IdolType

object IdolTypes {
  case object Cute extends IdolType
  case object Cool extends IdolType
  case object Sexy extends IdolType
  case object Pop extends IdolType
}


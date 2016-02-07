package walfie.photokatsu.models

sealed trait Attribute

object Attributes {
  case object Cute extends Attribute
  case object Cool extends Attribute
  case object Sexy extends Attribute
  case object Pop extends Attribute
}


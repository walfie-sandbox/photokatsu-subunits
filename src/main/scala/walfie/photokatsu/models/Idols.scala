package walfie.photokatsu.models

import enumeratum._
import walfie.photokatsu.models.Attributes._

sealed abstract class Idol(val attribute: Attribute) extends EnumEntry {
  lazy val name: String = this.toString
  lazy val toLong: Long = 1L << Idols.indexOf(this)
}

object Idol {
  val fromLong: Map[Long, Idol] =
    Idols.values.map(idol => idol.toLong -> idol).toMap
}

object Idols extends Enum[Idol] {
  case object Akari extends Idol(Cute)
  case object Aoi extends Idol(Cool)
  case object Hikari extends Idol(Sexy)
  case object Hinaki extends Idol(Pop)
  case object Ichigo extends Idol(Cute)
  case object Juri extends Idol(Sexy)
  case object Kaede extends Idol(Pop)
  case object Kii extends Idol(Pop)
  case object Kokone extends Idol(Pop)
  case object Madoka extends Idol(Cute)
  case object Maria extends Idol(Cute)
  case object Mikuru extends Idol(Pop)
  case object Miyabi extends Idol(Sexy)
  case object Mizuki extends Idol(Sexy)
  case object Nono extends Idol(Sexy)
  case object Otome extends Idol(Pop)
  case object Ran extends Idol(Sexy)
  case object Rin extends Idol(Cool)
  case object Risa extends Idol(Sexy)
  case object Sakura extends Idol(Cute)
  case object Seira extends Idol(Cool)
  case object Shion extends Idol(Cool)
  case object Sora extends Idol(Sexy)
  case object Sumire extends Idol(Cool)
  case object Yurika extends Idol(Cool)

  val values: Seq[Idol] = findValues

  implicit val idolOrdering: Ordering[Idol] = Ordering.by(_.name)
}


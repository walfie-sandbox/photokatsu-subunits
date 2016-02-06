package photokatsu.models

import photokatsu.util.Enum
import photokatsu.models.Attributes._

sealed trait Idol {
  def name: String
  def attribute: Attribute
  def toLong(): Long
}

object Idol {
  val fromLong: Map[Long, Idol] =
    Idols.values.map(idol => idol.toLong -> idol).toMap
}

object Idols extends Enum {
  sealed abstract class EnumVal(
    val name: String,
    val attribute: Attribute
  ) extends Value with Idol {
    def toLong(): Long = 1L << this.ordinal
  }

  case object Akari extends EnumVal("Akari", Cute)
  case object Aoi extends EnumVal("Aoi", Cool)
  case object Hikari extends EnumVal("Hikari", Sexy)
  case object Hinaki extends EnumVal("Hinaki", Pop)
  case object Ichigo extends EnumVal("Ichigo", Cute)
  case object Juri extends EnumVal("Juri", Sexy)
  case object Kaede extends EnumVal("Kaede", Pop)
  case object Kii extends EnumVal("Kii", Pop)
  case object Kokone extends EnumVal("Kokone", Pop)
  case object Madoka extends EnumVal("Madoka", Cute)
  case object Maria extends EnumVal("Maria", Cute)
  case object Mikuru extends EnumVal("Mikuru", Pop)
  case object Miyabi extends EnumVal("Miyabi", Sexy)
  case object Mizuki extends EnumVal("Mizuki", Sexy)
  case object Otome extends EnumVal("Otome", Pop)
  case object Ran extends EnumVal("Ran", Sexy)
  case object Rin extends EnumVal("Rin", Cool)
  case object Sakura extends EnumVal("Sakura", Cute)
  case object Seira extends EnumVal("Seira", Cool)
  case object Shion extends EnumVal("Shion", Cool)
  case object Sora extends EnumVal("Sora", Sexy)
  case object Sumire extends EnumVal("Sumire", Cool)
  case object Yurika extends EnumVal("Yurika", Cool)
}


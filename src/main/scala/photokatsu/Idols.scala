package photokatsu

import photokatsu.util.Enum

sealed trait Idol {
  def name: String
  def toLong(): Long
}

object Idol {
  val fromLong: Map[Long, Idol] =
    Idols.values.map(idol => idol.toLong -> idol).toMap
}

object Idols extends Enum {
  sealed abstract class EnumVal(val name: String) extends Value with Idol {
    def toLong(): Long = 1L << this.ordinal
  }

  case object Akari extends EnumVal("Akari")
  case object Aoi extends EnumVal("Aoi")
  case object Hikari extends EnumVal("Hikari")
  case object Hinaki extends EnumVal("Hinaki")
  case object Ichigo extends EnumVal("Ichigo")
  case object Juri extends EnumVal("Juri")
  case object Kaede extends EnumVal("Kaede")
  case object Kii extends EnumVal("Kii")
  case object Kokone extends EnumVal("Kokone")
  case object Madoka extends EnumVal("Madoka")
  case object Maria extends EnumVal("Maria")
  case object Mikuru extends EnumVal("Mikuru")
  case object Miyabi extends EnumVal("Miyabi")
  case object Mizuki extends EnumVal("Mizuki")
  case object Otome extends EnumVal("Otome")
  case object Ran extends EnumVal("Ran")
  case object Rin extends EnumVal("Rin")
  case object Sakura extends EnumVal("Sakura")
  case object Seira extends EnumVal("Seira")
  case object Shion extends EnumVal("Shion")
  case object Sora extends EnumVal("Sora")
  case object Sumire extends EnumVal("Sumire")
  case object Yurika extends EnumVal("Yurika")
}


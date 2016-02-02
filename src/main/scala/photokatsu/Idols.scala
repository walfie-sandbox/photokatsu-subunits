package photokatsu

import photokatsu.util.Enum

object Idol {
  import Idols.Idol

  val fromLong: PartialFunction[Long, Idol] =
    Idols.values.map(idol => idol.toLong -> idol).toMap
}

object Idols extends Enum {
  type Idol = Idols.EnumVal

  sealed abstract class EnumVal(val name: String) extends Value {
    def toLong(): Long = 1L << this.ordinal
  }

  case object Akari extends Idol("Akari")
  case object Aoi extends Idol("Aoi")
  case object Hikari extends Idol("Hikari")
  case object Hinaki extends Idol("Hinaki")
  case object Ichigo extends Idol("Ichigo")
  case object Juri extends Idol("Juri")
  case object Kaede extends Idol("Kaede")
  case object Kii extends Idol("Kii")
  case object Kokone extends Idol("Kokone")
  case object Madoka extends Idol("Madoka")
  case object Maria extends Idol("Maria")
  case object Mikuru extends Idol("Mikuru")
  case object Miyabi extends Idol("Miyabi")
  case object Mizuki extends Idol("Mizuki")
  case object Otome extends Idol("Otome")
  case object Ran extends Idol("Ran")
  case object Rin extends Idol("Rin")
  case object Sakura extends Idol("Sakura")
  case object Seira extends Idol("Seira")
  case object Shion extends Idol("Shion")
  case object Sora extends Idol("Sora")
  case object Sumire extends Idol("Sumire")
  case object Yurika extends Idol("Yurika")
}


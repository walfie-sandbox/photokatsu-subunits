package photokatsu

import photokatsu.Idols._

case class IdolUnit(members: Set[Idol], subunits: Seq[Subunit])

object IdolUnits {
  val MAX_SIZE = 8

  case class IdolUnitLongs(membersL: Long, subunitsL: Array[Long]) {
    def toIdolUnit(): IdolUnit = {
      val members: Set[Idol] = Idol.fromLong.withFilter {
        case (idolL: Long, idol: Idol) => (membersL & idolL) != 0
      }.map(_._2).toSet

      val subunits: Array[Subunit] = subunitsL.map(Subunit.fromLong)

      IdolUnit(members, subunits)
    }
  }

  def fromSubunits(subunits: Seq[Subunit], minSmile: Int = 1): Seq[IdolUnit] = {
    val subunitLongs: Array[Long] = subunits.map(_.toLong).toArray

    val idolUnitLs = for {
      smileCount: Int <- subunits.size.to(minSmile).by(-1)
      possibleSubunitsL <- subunitLongs.combinations(smileCount)
      membersL: Long = possibleSubunitsL.reduce(_ | _)
      if java.lang.Long.bitCount(membersL) <= MAX_SIZE
    } yield IdolUnitLongs(membersL, possibleSubunitsL)

    idolUnitLs.map(_.toIdolUnit)
  }
}


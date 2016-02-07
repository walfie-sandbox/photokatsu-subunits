package walfie.photokatsu.models

import walfie.photokatsu.models.Idols._

case class IdolUnit(members: Set[Idol], smiles: Seq[Smile])

object IdolUnits {
  val MAX_SIZE = 8

  private case class IdolUnitLongs(membersL: Long, smilesL: Array[Long]) {
    def toIdolUnit(): IdolUnit = {
      val members: Set[Idol] = Idol.fromLong.withFilter {
        case (idolL: Long, idol: Idol) => (membersL & idolL) != 0
      }.map(_._2).toSet

      val smiles: Array[Smile] = smilesL.map(Smile.fromLong)

      IdolUnit(members, smiles)
    }
  }

  def fromSmiles(smiles: Seq[Smile], minSmileCount: Int = 1): Seq[IdolUnit] = {
    val smileLongs: Array[Long] = smiles.map(_.toLong).toArray

    val idolUnitLs = for {
      smileCount: Int <- smiles.size.to(minSmileCount).by(-1) if smileCount > 0
      possibleSmilesL <- smileLongs.combinations(smileCount)
      membersL: Long = possibleSmilesL.reduce(_ | _)
      if java.lang.Long.bitCount(membersL) <= MAX_SIZE
    } yield IdolUnitLongs(membersL, possibleSmilesL)

    idolUnitLs.map(_.toIdolUnit)
  }

  def fromIdols(idols: Seq[Idol], minSmileCount: Int): Seq[IdolUnit] = {
    val smiles = Smiles.getPossibleSmiles(idols, Smiles.all)

    val units: Seq[IdolUnit] = for {
      unit <- IdolUnits.fromSmiles(smiles, minSmileCount)
    } yield {
      val extraSmiles: Seq[Smile] = Smiles.genericUnits(unit.members)
      unit.copy(smiles = unit.smiles ++ extraSmiles)
    }

    units.sortBy(-_.smiles.size)
  }
}


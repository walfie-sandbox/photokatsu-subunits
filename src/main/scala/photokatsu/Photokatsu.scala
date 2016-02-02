package photokatsu

import photokatsu.Idols._

object Photokatsu {
  def main(args: Array[String]): Unit = {
    val myIdols: Seq[Idol] = Seq(
      Akari, Ichigo, Maria, Yurika, Sumire, Rin,
      Seira, Shion, Sora,Ran, Kii, Mikuru, Mizuki, Kokone
    )

    val subunits = getPossibleSubunits(myIdols, Subunits.all)

    val units: Array[IdolUnit] = IdolUnits.fromSubunits(subunits, 1)

    for {
      unit <- units.toArray.sortBy(-_.subunits.size)
    } {
      println(unit.subunits.size)
      println(unit.members.mkString(", "))
      println(unit.subunits.map(_.name).mkString("(", ", ", ")"))
      println("=========")
    }
  }

  def getPossibleSubunits(
    idols: Seq[Idol],
    subunits: Seq[Subunit] = Subunits.all
  ): Seq[Subunit] = {
    val idolL: Long = idols.map(_.toLong).reduce(_ | _)

    for {
      subunitL: Long <- subunits.map(_.toLong)
      if (subunitL & idolL) == subunitL
    } yield Subunit.fromLong(subunitL)
  }
}


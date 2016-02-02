package photokatsu

import photokatsu.Idols._

object Photokatsu {
  def main(args: Array[String]): Unit = {
    /*
    val myIdols: Set[Idol] = Set(
      Akari, Ichigo, Maria, Yurika, Sumire, Rin,
      Seira, Shion, Sora, Ran, Kii,
      Mikuru, Mizuki, Kokone
    )
    */

    val myIdols: Seq[Idol] = scala.util.Random.shuffle(
      Seq(
        Akari, Aoi, Hikari, Hinaki, Ichigo,
        Juri, Kaede, Kii, Kokone, Madoka,
        Maria, Mikuru, Miyabi, Mizuki, Otome,
        Ran, Rin, Sakura, Seira, Shion,
        Sora, Sumire, Yurika
      )
    ).take(20)

    val prioritizedIdols: Set[Idol] = Set(
      Akari, Yurika, Sumire
    )

    val subunits = getPossibleSubunits(myIdols.toSeq, Subunits.all)

    val units: Array[IdolUnit] = IdolUnits.fromSubunits(subunits, 7)
      .sortBy(unit => -unit.subunits.size)
      .sortBy(unit => -prioritizedIdols.intersect(unit.members).size)

    for (unit <- units) {
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


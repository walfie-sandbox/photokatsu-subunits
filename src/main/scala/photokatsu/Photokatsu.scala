package photokatsu

import photokatsu.Idols._

object Photokatsu {
  def main(args: Array[String]): Unit = {
    val myIdols: Set[Idol] = Set(
      Akari, Ichigo, Maria, Yurika, Sumire, Rin,
      Seira, Shion, Sora, Ran, Kii,
      Mikuru, Mizuki, Kokone, Otome, Sora,
      Aoi, Juri, Hikari
    )

    /*
    val myIdols: Seq[Idol] = scala.util.Random.shuffle(
      Seq(
        Akari, Aoi, Hikari, Hinaki, Ichigo,
        Juri, Kaede, Kii, Kokone, Madoka,
        Maria, Mikuru, Miyabi, Mizuki, Otome,
        Ran, Rin, Sakura, Seira, Shion,
        Sora, Sumire, Yurika
      )
    ).take(20)
    */

    val prioritizedIdols: Set[Idol] = Set(
      Akari, Yurika, Sumire, Sora
    )

    val requiredIdols: Set[Idol] = Set(
      Akari
    )

    val subunits = getPossibleSubunits(myIdols.toSeq, Subunits.all)

    val units: Array[IdolUnit] = IdolUnits.fromSubunits(subunits, 6)
      .filter(unit => requiredIdols.subsetOf(unit.members))
      .sortBy(unit => -unit.subunits.size)
      .sortBy(unit => -prioritizedIdols.intersect(unit.members).size)

    for (unit <- units.take(10)) {
      val important = prioritizedIdols.intersect(unit.members)

      println(s"${unit.subunits.size} (${important.size})")
      println("contains " + important.mkString(", "))
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


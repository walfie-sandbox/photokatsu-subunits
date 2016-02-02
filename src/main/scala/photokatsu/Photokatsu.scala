package photokatsu

import photokatsu.Idols._

object Photokatsu {
  def main(args: Array[String]): Unit = {
    println(getPossibleSubunits(Set(Akari)).toList)
  }

  def getPossibleSubunits(
    idols: Set[Idol],
    subunits: Seq[Subunit] = Subunits.all
  ): Array[Subunit] = for {
    iLong: Long <- idols.map(_.toLong).toArray
    sLong: Long <- subunits.map(_.toLong)

    if (sLong & iLong) == iLong
  } yield Subunit.fromLong(sLong)
}


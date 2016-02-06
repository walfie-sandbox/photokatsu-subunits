package photokatsu

import photokatsu.Idols._
import photokatsu.util.BooleanOps._

case class Subunit(name: String, members: Set[Idol]) {
  def toLong(): Long = members.map(_.toLong).reduce(_ | _)
}

object Subunit {
  def apply(name: String, members: Idol*): Subunit = apply(name, members.toSet)

  val fromLong: PartialFunction[Long, Subunit] =
    Subunits.all.map(s => s.toLong -> s).toMap
}

object Subunits {
  lazy val all: Array[Subunit] = Array(
    Subunit("STAR☆ANIS", Ichigo, Aoi, Ran, Otome, Yurika, Sakura, Kaede, Mizuki),
    Subunit("AIKATSU8 2014", Ichigo, Seira, Mizuki, Mikuru, Sora, Maria, Otome, Yurika),
    Subunit("AIKATSU8 2015", Ichigo, Aoi, Ran, Yurika, Sakura, Mizuki, Akari, Sumire),
    Subunit("Vampire Mystery", Akari, Sumire, Hinaki, Juri),
    Subunit("Oshare Tankentai Cool Angels", Seira, Kii, Sora, Maria),
    Subunit("Soleil", Ichigo, Aoi, Ran),
    Subunit("Tristar", Mizuki, Kaede, Yurika),
    Subunit("Powax2PuRiRiN", Otome, Sakura, Shion),
    Subunit("Luminas", Akari, Sumire, Hinaki),
    Subunit("Vanilla Chili Pepper", Juri, Madoka, Rin),
    Subunit("Vampire Gakuen", Yurika, Mizuki, Sumire),
    Subunit("Tsunagaru Baton", Ichigo, Akari, Mizuki),
    Subunit("2wingS", Ichigo, Seira),
    Subunit("WM", Mizuki, Mikuru),
    Subunit("DansingDiva", Sumire, Rin),
    Subunit("Jonetsu Jalapeño", Juri, Hinaki),
    Subunit("Skips♪", Akari, Madoka),
    Subunit("Amafuwa☆Nadeshiko", Miyabi, Kokone),
    Subunit("Producers", Aoi, Kii),
    Subunit("Ikenai Keiji", Aoi, Shion),
    Subunit("Oshare Kaito Swallowtail (Shin)", Ichigo, Otome),
    Subunit("Fushigi no Kuni no Alice", Ichigo, Shion),
    Subunit("ChocoPop Tantei", Seira, Kii),
    Subunit("Tsuki no Sabaku no Gensokyoku", Ran, Sora),
    Subunit("Aikatsu! Sensei", Akari, Juri),
    Subunit("Otome to Hinaki no Mattari Saka", Otome, Hinaki),
    Subunit("Gogo wa Ichi Ichi Ichigo Kibun", Ichigo),
    Subunit("Chika no Taiyo", Hikari)
  )

  def genericUnits(idols: Set[Idol]): Seq[Subunit] = {
    val checks: List[Set[Idol] => Option[Subunit]] = List(
      starlightQueen _,
      sameAttribute _,
      allStarlight _,
      allTypes _
    )

    checks.flatMap(_.apply(idols))
  }

  def starlightQueen(idols: Set[Idol]): Option[Subunit] = {
    val queens = idols.intersect(Set(Mizuki, Otome, Sakura))
    queens.nonEmpty.option(Subunit("Starlight Queen", queens))
  }

  def sameAttribute(idols: Set[Idol]): Option[Subunit] = {
    if (idols.size == IdolUnits.MAX_SIZE) {
      val attr = idols.head.attribute
      idols.forall(idol => idol.attribute == attr).option {
        Subunit(s"$attr Style", idols)
      }
    } else None
  }

  def allStarlight(idols: Set[Idol]): Option[Subunit] = {
    val nonStarlight: Set[Idol] = Set(
      Mizuki, Seira, Kii, Sora, Maria, Mikuru
    )

    idols.intersect(nonStarlight).isEmpty.option {
      Subunit("All Starlight", idols)
    }
  }

  def allTypes(idols: Set[Idol]): Option[Subunit] = {
    (idols.map(_.attribute).size == 4).option {
      Subunit("All Types", idols)
    }
  }
}


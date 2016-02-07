package walfie.photokatsu.models

import walfie.photokatsu.models.Idols._
import walfie.photokatsu.util.BooleanOps._

case class Smile(name: String, members: Set[Idol]) {
  def toLong(): Long = members.map(_.toLong).reduce(_ | _)
}

object Smile {
  def apply(name: String, members: Idol*): Smile = apply(name, members.toSet)

  val fromLong: PartialFunction[Long, Smile] =
    Smiles.all.map(s => s.toLong -> s).toMap
}

object Smiles {
  lazy val all: Array[Smile] = Array(
    Smile("STAR☆ANIS", Ichigo, Aoi, Ran, Otome, Yurika, Sakura, Kaede, Mizuki),
    Smile("AIKATSU8 2014", Ichigo, Seira, Mizuki, Mikuru, Sora, Maria, Otome, Yurika),
    Smile("AIKATSU8 2015", Ichigo, Aoi, Ran, Yurika, Sakura, Mizuki, Akari, Sumire),
    Smile("Vampire Mystery", Akari, Sumire, Hinaki, Juri),
    Smile("Oshare Tankentai Cool Angels", Seira, Kii, Sora, Maria),
    Smile("Soleil", Ichigo, Aoi, Ran),
    Smile("Tristar", Mizuki, Kaede, Yurika),
    Smile("Powax2PuRiRiN", Otome, Sakura, Shion),
    Smile("Luminas", Akari, Sumire, Hinaki),
    Smile("Vanilla Chili Pepper", Juri, Madoka, Rin),
    Smile("Vampire Gakuen", Yurika, Mizuki, Sumire),
    Smile("Tsunagaru Baton", Ichigo, Akari, Mizuki),
    Smile("2wingS", Ichigo, Seira),
    Smile("WM", Mizuki, Mikuru),
    Smile("DansingDiva", Sumire, Rin),
    Smile("Jonetsu Jalapeño", Juri, Hinaki),
    Smile("Skips♪", Akari, Madoka),
    Smile("Amafuwa☆Nadeshiko", Miyabi, Kokone),
    Smile("Producers", Aoi, Kii),
    Smile("Ikenai Keiji", Aoi, Shion),
    Smile("Oshare Kaito Swallowtail (Shin)", Ichigo, Otome),
    Smile("Fushigi no Kuni no Alice", Ichigo, Shion),
    Smile("ChocoPop Tantei", Seira, Kii),
    Smile("Tsuki no Sabaku no Gensokyoku", Ran, Sora),
    Smile("Aikatsu! Sensei", Akari, Juri),
    Smile("Otome to Hinaki no Mattari Saka", Otome, Hinaki),
    Smile("Gogo wa Ichi Ichi Ichigo Kibun", Ichigo),
    Smile("Chika no Taiyo", Hikari)
  )

  def genericUnits(idols: Set[Idol]): Seq[Smile] = {
    val checks: List[Set[Idol] => Option[Smile]] = List(
      starlightQueen _,
      sameAttribute _,
      allStarlight _,
      allTypes _
    )

    checks.flatMap(_.apply(idols))
  }

  def getPossibleSmiles(
    idols: Seq[Idol],
    smiles: Seq[Smile] = Smiles.all
  ): Seq[Smile] = if (idols.isEmpty) {
    Seq.empty
  } else {
    val idolL: Long = idols.map(_.toLong).reduce(_ | _)

    for {
      smileL: Long <- smiles.map(_.toLong)
      if (smileL & idolL) == smileL
    } yield Smile.fromLong(smileL)
  }

  private def starlightQueen(idols: Set[Idol]): Option[Smile] = {
    val queens = idols.intersect(Set(Mizuki, Otome, Sakura))
    queens.nonEmpty.option(Smile("Starlight Queen", queens))
  }

  private def sameAttribute(idols: Set[Idol]): Option[Smile] = {
    if (idols.size == IdolUnits.MAX_SIZE) {
      val attr = idols.head.attribute
      idols.forall(idol => idol.attribute == attr).option {
        Smile(s"$attr Style", idols)
      }
    } else None
  }

  private def allStarlight(idols: Set[Idol]): Option[Smile] = {
    val nonStarlight: Set[Idol] = Set(
      Mizuki, Seira, Kii, Sora, Maria, Mikuru
    )

    idols.intersect(nonStarlight).isEmpty.option {
      Smile("All Starlight", idols)
    }
  }

  private def allTypes(idols: Set[Idol]): Option[Smile] = {
    (idols.map(_.attribute).size == 4).option {
      Smile("All Types", idols)
    }
  }
}


package photokatsu

import photokatsu.Idols._

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
    Subunit("Soleil", Ichigo, Aoi, Ran),
    Subunit("Tristar", Mizuki, Kaede, Yurika),
    Subunit("Powax2PuRiRiN", Otome, Sakura, Shion),
    Subunit("Luminas", Akari, Sumire, Hinaki),
    Subunit("Vanilla Chili Pepper", Juri, Madoka, Rin),
    Subunit("Vampire Academy", Yurika, Mizuki, Sumire),
    Subunit("Tsunagaru Line", Ichigo, Akari, Mizuki),
    Subunit("2wingS", Ichigo, Seira),
    Subunit("WM", Mizuki, Mikuru),
    Subunit("DansingDiva", Sumire, Rin),
    Subunit("Jonetsu Jalapeño", Juri, Hinaki),
    Subunit("Skips♪", Akari, Madoka),
    Subunit("Amafuwa☆Nadeshiko", Miyabi, Kokone),
    Subunit("Producers", Aoi, Kii),
    Subunit("Ikenai Keiji", Aoi, Shion),
    Subunit("Oshare Kaito Swallowtailshin", Ichigo, Otome),
    Subunit("Fushigi no Kuni no Alice", Ichigo, Shion),
    Subunit("ChocoPop Tantei", Seira, Kii),
    Subunit("Tsuki no Sabaku no Gensokyoku", Ran, Sora),
    Subunit("Aikatsu! Sensei", Akari, Juri),
    Subunit("Tsunagaru Baton", Ichigo, Akari),
    Subunit("Otome to Hinaki no Mattari Saka", Otome, Hinaki),
    Subunit("Gogo wa Ichi Ichi Ichigo Kibun", Ichigo),
    Subunit("Chika no Taiyo", Hikari)
  )

}


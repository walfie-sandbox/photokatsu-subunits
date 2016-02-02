package photokatsu

import photokatsu.Idols._

case class IdolUnit(members: Set[Idol], subunits: Array[Subunit])

object IdolUnits {
  val MAX_SIZE = 8
}


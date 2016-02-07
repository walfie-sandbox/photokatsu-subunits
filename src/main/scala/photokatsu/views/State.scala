package photokatsu.views

import photokatsu.models.Idol

case class State(
  idols: Map[Idol, Boolean],
  minSmile: Int
)


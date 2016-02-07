package photokatsu.views

import photokatsu.models.Idol

case class State(
  minimumSmile: Int = 5,
  selectedIdols: Set[Idol] = Set.empty
)


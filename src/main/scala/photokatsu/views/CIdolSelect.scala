package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import photokatsu.models._

/*
object CIdolSelect {
  val component = ReactComponentB[Unit]("IdolSelect")
    .initialState(Set[Idol]())
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Unit, Set[Idol]]) {
    def render(s: Set[Idol]): ReactElement = {
      <.div(
        Idols.values.map { idol: Idol =>
          <.label(
            <.input(
              ^.`type` := "checkbox",
              ^.checked := s.contains(idol)
            ),
            idol.name
          )
        }
      )
    }
  }
}
*/


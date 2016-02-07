package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import photokatsu.models._
import photokatsu.models.Idols._

object CRoot {
  val component = ReactComponentB[Unit]("Echo")
    .initialState(State(5, Set.empty))
    .renderBackend[Backend]
    .buildU

  class Backend($: BackendScope[Unit, State]) {
    def render(s: State): ReactElement = <.div(
      idolCheckboxes(s.selectedIdols)
    )

    def idolCheckboxes(selected: Set[Idol]): ReactElement = <.div {
      Idols.values.map { idol: Idol =>
        <.label(
          <.input(
            ^.`type` := "checkbox",
            ^.checked := selected.contains(idol)
          ),
          idol.name
        )
      }
    }

  }

}


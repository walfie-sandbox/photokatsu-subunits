package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLInputElement
import photokatsu.models._
import photokatsu.models.Idols._

object CRoot {
  val defaultMinSmile: Int = 5
  val initialIdols: Map[Idol, Boolean] = Idols.values.map(_ -> false).toMap

  val component = ReactComponentB[Unit]("Echo")
    .initialState(State(initialIdols, defaultMinSmile))
    .renderBackend[Backend]
    .buildU

  class Backend($: BackendScope[Unit, State]) {
    val minSmileRef = Ref[HTMLInputElement]("minSmile")
    // get value with: minSmileRef($).map(_.value)

    def render(s: State): ReactElement = <.div(
      <.input(
        ^.ref := minSmileRef,
        ^.defaultValue := minSmile,
        ^.`type` := "number"
      ),
      idolCheckboxes(s.idols)
    )

    def idolCheckboxes(idols: Map[Idol, Boolean]): ReactElement = <.div(
      idols.map { case (idol: Idol, isSelected: Boolean) =>
        <.div(
          ^.key := idol.name,
          <.label(
            <.input(
              ^.`type` := "checkbox",
              ^.checked := isSelected,
              ^.onChange --> $.modState { s: State =>
                s.copy(idols = idols.updated(idol, !isSelected))
              }
            ),
            idol.name
          )
        )
      }
    )

  }

}


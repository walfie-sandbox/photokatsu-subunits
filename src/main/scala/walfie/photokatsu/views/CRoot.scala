package walfie.photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.ext.Storage
import walfie.photokatsu.models._

object CRoot {
  case class Props(storage: Option[Storage])
  protected case class State(units: Seq[IdolUnit])

  val component = ReactComponentB[Props]("Root")
    .initialState(State(Seq.empty))
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(p: Props, s: State): ReactElement = <.div(
      CIdolInputs(CIdolInputs.Props(storage = p.storage, onSubmit = onSubmit)),
      CUnitOutputs(CUnitOutputs.Props(s.units))
    )

    def onSubmit(idols: Seq[Idol], minSmile: Int): Callback = {
      val units = IdolUnits.fromIdols(idols, minSmile) // TODO: Progress bar
      $.modState { s: State =>
        s.copy(units = units)
      }
    }

  }
}



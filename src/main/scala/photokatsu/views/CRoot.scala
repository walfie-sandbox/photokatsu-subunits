package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import photokatsu.models._

object CRoot {
  protected case class State(units: Seq[IdolUnit])

  val component = ReactComponentB[Unit]("Root")
    .initialState(State(Seq.empty))
    .renderBackend[Backend]
    .buildU

  class Backend($: BackendScope[Unit, State]) {
    def render(s: State): ReactElement = <.div(
      CIdolInputs(CIdolInputs.Props(onSubmit = onSubmit)),
      CUnitOutputs(CUnitOutputs.Props(s.units))
    )

    def onSubmit(idols: Seq[Idol], minSmile: Int): Callback = {
      val units = IdolUnits.fromIdols(idols, minSmile) // TODO: Progress bar
      $.modState { s: State =>
        s.copy(units = units)
      }
    }

  }

  def apply(): ReactElement = component()
}



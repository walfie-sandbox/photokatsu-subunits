package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLInputElement
import photokatsu.models._
import photokatsu.models.Idols._
import scala.collection.immutable.SortedMap

object CRoot {
  protected case class State(units: Seq[IdolUnit])

  val component = ReactComponentB[Unit]("Root")
    .initialState(State(Seq.empty))
    .renderBackend[Backend]
    .buildU

  class Backend($: BackendScope[Unit, State]) {
    def render(s: State): ReactElement = {
      CIdolInputs(CIdolInputs.Props(
        onSubmit = onSubmit
      ))
    }

    def onSubmit(idols: Seq[Idol], minSmile: Int): Callback = {
      val units = IdolUnits.fromIdols(idols, minSmile) // TODO: Progress bar
      println(units)
      $.modState { s: State =>
        s.copy(units = units)
      }
    }

  }

  def apply(): ReactElement = component()
}



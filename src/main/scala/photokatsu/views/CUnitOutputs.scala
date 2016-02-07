package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import photokatsu.models._
import photokatsu.models.Idols._

object CUnitOutputs {
  case class Props(units: Seq[IdolUnit])

  val component = ReactComponentB[Props]("UnitOutputs")
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, Unit]) {
    def render(p: Props): ReactElement = <.div {
      p.units.map { unit: IdolUnit =>
        <.div(
          unit.members.mkString(", "),
          s" (${unit.smiles.size} smile)",
          <.ol(
            unit.smiles.map { smile: Smile =>
              <.li(
                smile.name,
                " ",
                smile.members.mkString("(", ", ", ")")
              )
            }
          )
        )
      }
    }
  }

  def apply(p: Props): ReactElement = component(p)
}


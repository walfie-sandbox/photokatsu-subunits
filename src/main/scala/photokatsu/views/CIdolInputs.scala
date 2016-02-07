package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLInputElement
import photokatsu.models._
import photokatsu.models.Idols._
import scala.collection.immutable.SortedMap

object CIdolInputs {
  val defaultMinSmile: Int = 5
  val initialIdols: Map[Idol, Boolean] = SortedMap(Idols.values.map(_ -> false): _*)

  protected case class State(idols: Map[Idol, Boolean])
  case class Props(onSubmit: (Seq[Idol], Int) => Callback)

  val component = ReactComponentB[Props]("IdolInputs")
    .initialState(State(initialIdols))
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    val minSmileRef = Ref[HTMLInputElement]("minSmile")
    // get value with: minSmileRef($).map(_.value)

    def render(p: Props, s: State): ReactElement = {
      val idolCheckboxes = s.idols.map { case (idol: Idol, isSelected: Boolean) =>
        CIdolSelect(CIdolSelect.Props(
          idol = idol,
          isSelected = isSelected,
          onToggle = $.modState { s: State =>
            s.copy(idols = s.idols.updated(idol, !isSelected))
          }
        ))
      }

      val minSmileInput = <.input(
        ^.ref := minSmileRef,
        ^.defaultValue := defaultMinSmile,
        ^.`type` := "number"
      )

      <.form(
        ^.onSubmit ==> { e: ReactEventI =>
          val idols: Seq[Idol] = s.idols.filter(_._2).keys.toSeq
          val minSmile: Int = minSmileRef($).map(_.valueAsNumber).get
          e.preventDefaultCB >> p.onSubmit(idols, minSmile)
        },
        minSmileInput,
        idolCheckboxes,
        <.button("Submit")
      )
    }
  }

  def apply(p: Props): ReactElement = component(p)
}


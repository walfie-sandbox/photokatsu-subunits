package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLInputElement
import photokatsu.models._
import photokatsu.models.Idols._
import scala.collection.immutable.SortedMap

object CRoot {
  val defaultMinSmile: Int = 5
  val initialIdols: Map[Idol, Boolean] = SortedMap(Idols.values.map(_ -> false): _*)

  case class State(idols: Map[Idol, Boolean])

  val component = ReactComponentB[Unit]("Echo")
    .initialState(State(initialIdols))
    .renderBackend[Backend]
    .buildU

  class Backend($: BackendScope[Unit, State]) {
    val minSmileRef = Ref[HTMLInputElement]("minSmile")
    // get value with: minSmileRef($).map(_.value)

    def render(s: State): ReactElement = <.form(
      ^.onSubmit ==> handleSubmit,
      <.input(
        ^.ref := minSmileRef,
        ^.defaultValue := defaultMinSmile,
        ^.`type` := "number"
      ),
      s.idols.map { case (idol: Idol, isSelected: Boolean) =>
        CIdolSelect(CIdolSelect.Props(
          idol,
          isSelected,
          $.modState { s: State =>
            s.copy(idols = s.idols.updated(idol, !isSelected))
          }
        ))
      },
      <.button("Submit")
    )

    def handleSubmit(e: ReactEventI): Callback = {
      e.preventDefaultCB >> $.modState { s: State =>
        val minSmile: Int = minSmileRef($).map(_.valueAsNumber).get
        val selectedIdols: Seq[Idol] = s.idols.filter(_._2).keys.toSeq
        println(IdolUnits.fromIdols(selectedIdols, minSmile))
        s
      }
    }

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


package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLInputElement
import org.scalajs.dom.ext.Storage
import photokatsu.models._
import photokatsu.models.Idols._
import scala.collection.immutable.SortedMap

object CIdolInputs {
  val defaultMinSmile: Int = 5
  val initialIdols: Map[Idol, Boolean] = SortedMap(Idols.values.map(_ -> false): _*)

  protected case class State(idols: Map[Idol, Boolean])
  case class Props(
    onSubmit: (Seq[Idol], Int) => Callback,
    storage: Option[Storage]
  )

  val component = ReactComponentB[Props]("IdolInputs")
    .initialState_P { p: Props =>
      val defaultState = State(initialIdols)
      p.storage.fold(defaultState)(storage => loadState(storage, defaultState))
    }
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    val minSmileRef = Ref[HTMLInputElement]("minSmile")

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
          saveIdols(p.storage, idols)
          e.preventDefaultCB >> p.onSubmit(idols, minSmile)
        },
        minSmileInput,
        idolCheckboxes,
        <.button("Submit")
      )
    }
  }

  protected val LOCALSTORAGE_KEY = "inputIdols"
  def loadIdols(storage: Storage): Seq[Idol] = {
    val idolNames: Seq[String] = storage(LOCALSTORAGE_KEY)
      .fold(Seq[String]())(_.split(","))

    for {
      idolName <- idolNames
      idol <- Idols.withNameOption(idolName.trim)
    } yield idol
  }

  def loadState(storage: Storage, state: State): State = {
    val storedIdols: Seq[Idol] = loadIdols(storage)

    val newMap: Map[Idol, Boolean] = state.idols.map { case (idol, _) =>
      idol -> storedIdols.contains(idol)
    }.toMap

    state.copy(idols = newMap)
  }

  def saveIdols(
    storageOpt: Option[Storage],
    idols: Seq[Idol]
  ): Unit = storageOpt.map { storage: Storage =>
    val serializedIdols: String = idols.map(_.name).mkString(",")
    storage.update(LOCALSTORAGE_KEY, serializedIdols)
  }

  def apply(p: Props): ReactElement = component(p)
}


package walfie.photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.ext.Storage
import org.scalajs.dom.raw.HTMLInputElement
import scala.collection.immutable.SortedMap
import walfie.photokatsu.models._
import walfie.photokatsu.models.Idols._
import walfie.photokatsu.util.mdl._

object CIdolInputs {
  val defaultMinSmile: Int = 8
  val initialIdols: SortedMap[Idol, Boolean] = SortedMap(Idols.values.map(_ -> false): _*)

  protected case class State(
    idols: SortedMap[Idol, Boolean],
    minSmile: Int
  )

  case class Props(
    onSubmit: (Seq[Idol], Int) => Callback,
    storage: Option[Storage]
  )

  val component = ReactComponentB[Props]("IdolInputs")
    .initialState_P { p: Props =>
      val defaultState = State(initialIdols, defaultMinSmile)
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
        ^.defaultValue := s.minSmile,
        ^.`type` := "number"
      )

      def onSubmit(e: ReactEventI): Callback = {
        val idols: Seq[Idol] = s.idols.filter(_._2).keys.toSeq
        val minSmile: Int = minSmileRef($).map(_.valueAsNumber).get

        e.preventDefaultCB >>
          p.onSubmit(idols, minSmile) >>
          $.modState { s: State =>
            val newState = s.copy(minSmile = minSmile)
            saveState(p.storage, newState)
            newState
          }
      }

      <.form(
        ^.onSubmit ==> onSubmit,
        minSmileInput,
        idolCheckboxes,
        <.button(
          "Submit",
          ^.className := "mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
        ).mdl
      )
    }
  }

  protected val STORAGEKEY_IDOLS = "inputIdols"
  protected val STORAGEKEY_MIN_SMILE = "inputMinSmile"
  def loadIdols(storage: Storage): Seq[Idol] = {
    val idolNames: Seq[String] = storage(STORAGEKEY_IDOLS)
      .fold(Seq[String]())(_.split(","))

    for {
      idolName <- idolNames
      idol <- Idols.withNameOption(idolName.trim)
    } yield idol
  }

  def loadState(storage: Storage, fallbackState: State): State = {
    val storedIdols: Seq[Idol] = loadIdols(storage)
    val minSmile: Int = try {
      storage(STORAGEKEY_MIN_SMILE).map(_.toInt).getOrElse(fallbackState.minSmile)
    } catch {
      case e: NumberFormatException => fallbackState.minSmile
    }

    val newIdols: SortedMap[Idol, Boolean] = fallbackState.idols.map {
      case (idol, _) => idol -> storedIdols.contains(idol)
    }

    fallbackState.copy(idols = newIdols, minSmile = minSmile)
  }

  def saveState(
    storageOpt: Option[Storage],
    state: State
  ): Unit = storageOpt.map { storage: Storage =>
    val serializedIdols: String = (for {
      (idol, isSelected) <- state.idols if isSelected
    } yield idol.name).mkString(",")
    storage.update(STORAGEKEY_IDOLS, serializedIdols)
    storage.update(STORAGEKEY_MIN_SMILE, state.minSmile.toString)
  }

  def apply(p: Props): ReactElement = component(p)
}


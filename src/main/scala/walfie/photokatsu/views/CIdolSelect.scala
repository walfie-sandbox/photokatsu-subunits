package walfie.photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.photokatsu.models._
import walfie.photokatsu.util.mdl.ToMDL

object CIdolSelect {
  case class Props(
    idol: Idol,
    isSelected: Boolean,
    onToggle: Callback
  )

  val component = ReactComponentB[Props]("IdolSelect")
    .render_P { p: Props =>
      <.label(
        ^.className := "mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect",
        <.input(
          ^.`type` := "checkbox",
          ^.className := "mdl-checkbox__input",
          ^.checked := p.isSelected,
          ^.onChange --> p.onToggle
        ),
        p.idol.name
      )
    }
    .build

  def apply(p: Props): ReactElement =
    component.withKey(p.idol.name)(p)
}


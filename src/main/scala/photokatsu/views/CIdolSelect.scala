package photokatsu.views

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import photokatsu.models._

object CIdolSelect {
  case class Props(
    idol: Idol,
    isSelected: Boolean,
    onToggle: Callback
  )

  val component = ReactComponentB[Props]("IdolSelect")
    .render_P { p: Props =>
      <.label(
        <.input(
          ^.`type` := "checkbox",
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


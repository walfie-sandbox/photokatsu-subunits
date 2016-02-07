package walfie.photokatsu.util.mdl

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scala.scalajs.js

object MaterialDesignLite {
  // Props, State, Backend, Node
  type ScopeT = CompScope.DuringCallbackM[ReactTag, Unit, Unit, TopNode]

  val component = ReactComponentB[ReactTag]("MDLComponent")
    .render_P(identity)
    .componentDidMount(upgrade)
    .componentDidUpdate(c => upgrade(c.$))
    .build

  def upgrade(scope: ScopeT): Callback = Callback {
    js.Dynamic.global.componentHandler.upgradeElement(scope.getDOMNode)
  }

  def apply(tag: ReactTag) = this.component(tag)
}


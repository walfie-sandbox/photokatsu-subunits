package walfie.photokatsu.util

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

package object mdl {
  implicit class ToMDL(val elem: ReactTag) extends AnyVal {
    def mdl(): ReactComponentU[ReactTag, Unit, Unit, TopNode] =
      MaterialDesignLite(elem)
  }
}


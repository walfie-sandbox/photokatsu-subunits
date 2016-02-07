package walfie.photokatsu

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.document
import org.scalajs.dom.ext.LocalStorage
import scala.scalajs.js.JSApp
import walfie.photokatsu.models._
import walfie.photokatsu.models.Idols._
import walfie.photokatsu.views._

object Photokatsu extends JSApp {
  def main(): Unit = {
    val holder = document.querySelector("#content")
    val storage = Option(LocalStorage)
    val root = CRoot.component(CRoot.Props(storage))

    ReactDOM.render(root, holder)
  }
}


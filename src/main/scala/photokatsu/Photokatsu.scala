package photokatsu

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.document
import photokatsu.models._
import photokatsu.models.Idols._
import photokatsu.views._
import scala.scalajs.js.JSApp

object Photokatsu extends JSApp {
  def main(): Unit = {
    val holder = document.querySelector("#content")
    ReactDOM.render(CRoot.component(), holder)
  }

  def test(): Unit = {
    val myIdols: Seq[Idol] = Set(
      Akari, Ichigo, Maria, Yurika, Sumire,
      Rin, Seira, Shion, Sora, Ran,
      Kii, Mikuru, Mizuki, Kokone, Otome,
      Sora, Aoi, Juri, Hikari
    ).toSeq

    val units = IdolUnits.fromIdols(myIdols, 6)
    // TODO: filter by required/important idols

    for (unit <- units) {
      println(s"${unit.smiles.size}")
      println(unit.members.mkString(", "))
      println(unit.smiles.map(_.name).mkString("(", ", ", ")"))
      println("=========")
    }
  }
}


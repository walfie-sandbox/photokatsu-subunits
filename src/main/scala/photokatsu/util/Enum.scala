package photokatsu.util

// https://gist.github.com/d6y/376f1a4b178c343ff415
trait Enum {
  import java.util.concurrent.atomic.AtomicReference

  type EnumVal <: Value

  private val _values = new AtomicReference(Vector[EnumVal]())

  private final def addEnumVal(newVal: EnumVal): Int = {
    val oldVec = _values.get
    val newVec = oldVec :+ newVal
    if (_values.get.eq(oldVec) && _values.compareAndSet(oldVec, newVec)) {
      newVec.indexWhere(_ eq newVal)
    } else {
      addEnumVal(newVal)
    }
  }

  def values: Vector[EnumVal] = _values.get

  protected trait Value extends Ordered[Value] { self: EnumVal =>
    final val ordinal = addEnumVal(this)

    def compare(that: Value) = this.ordinal - that.ordinal

    def name: String

    override def toString = name
    override def equals(other: Any) = this eq other.asInstanceOf[AnyRef]
    override def hashCode = 31 * (this.getClass.## + name.## + ordinal)
  }
}


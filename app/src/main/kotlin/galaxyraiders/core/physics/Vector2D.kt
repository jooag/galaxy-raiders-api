package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt(this.dx * scalar, this.dy * scalar)

  val radiant: Double
    get() = atan(dy/dx)

  val degree: Double
    get() = this.radiant * 180 / PI

  val unit: Vector2D
    get() = this / this.magnitude 

  val normal: Vector2D
    get() = Vector2D(dy, dx)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(this.dx * scalar, this.dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return this.dx * v.dx + this.dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(this.dx + v.dx, this.dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.dx, this.dy + p.dy)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(- this.dx, - this.dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return (this * target) / this.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (this.scalarProject(target) * this) / this.magnitude
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}

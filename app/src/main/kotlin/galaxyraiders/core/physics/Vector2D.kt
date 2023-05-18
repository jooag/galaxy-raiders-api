package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.atan2
import kotlin.math.sqrt
import kotlin.math.PI

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=${this.dx}, dy=${this.dy})"
  }

  val magnitude: Double
    get() = sqrt(this.dx * this.dx + this.dy * this.dy)

  val radiant: Double    
    get() = atan2(this.dy, this.dx) 
    
  val degree: Double
    get(){
      val halfRound = 180
      return this.radiant * halfRound / PI
    }

  val unit: Vector2D
    get() = this / this.magnitude 
    
  val normal: Vector2D
    get() = Vector2D(this.dy, -this.dx) / this.magnitude

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
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(- this.dx, - this.dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return (this * target) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (this.scalarProject(target) * target) / target.magnitude
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}

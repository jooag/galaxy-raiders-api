package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + this.x, p.y + this.y)
  }

  operator fun plus(v: Vector2D): Point2D {
    return Point2D(v.dx + this.x, v.dy + this.y);
  }

  override fun toString(): String {
    return "Point2D(x=${this.x}, y=$${this.y})"
  }

  fun toVector(): Vector2D {
    return Vector2D(this.x, this.y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D(this.x - p.x, this.y - p.y)
  }

  fun impactDirection(p: Point2D): Vector2D {
    val v = impactDirection(p)
    return v/v.magnitude
  }

  fun contactVector(p: Point2D): Vector2D {
    return impactVector(p)
  }

  fun contactDirection(p: Point2D): Vector2D {
    return impactDirection(p)
  }

  fun distance(p: Point2D): Double {
    return this.impactVector(p).magnitude
  }
}

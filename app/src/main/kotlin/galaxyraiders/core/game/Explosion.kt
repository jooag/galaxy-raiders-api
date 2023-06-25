package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  initialVelocity: Vector2D,  
  radius: Double,
  val duration: Long
) :
  SpaceObject("Explosion", 'E', initialPosition, initialVelocity, radius, 0.0)
{
  val startTime = System.currentTimeMillis()

  fun isOver() : Boolean{
    return (System.currentTimeMillis() - startTime) > this.duration
  }



}

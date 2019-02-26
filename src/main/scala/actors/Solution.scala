package actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage

class Solution(image: WritableImage) extends Actor
{
  import Solution._
  
  def receive =
  {
    case CheckLocation(x, y) =>
      
    case m => println("Unhandled message in Solution: " + m)
  }
}

object Solution
{
  case class CheckLocation(x: Int, y: Int)
}
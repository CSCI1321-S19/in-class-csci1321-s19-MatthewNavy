package mud

import akka.actor.Actor
import akka.actor.ActorRef

class Player extends Actor
{
  import Player._
  
  def receive =
  {
    case ProcessCommand => ???
    case PrintMessage(msg) => println(msg)
    case TakeExit(room) => ???
    case TakeItem(item) => ???
    case m => println("Player got an unhandled message: " + m)
  }
}

object Player
{
  case object ProcessCommand
  case class PrintMessage(msg: String)
  case class TakeExit(room: Option[ActorRef])
  case class TakeItem(item: Option[Item])
}
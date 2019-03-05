package mud

import scala.collection.mutable.Buffer
import akka.actor.Actor
import akka.actor.ActorRef

class Room(
    val name: String,
    val desc: String,
    private var items: Buffer[Item],
    private val exits: Array[String]) extends Actor
{
  import Room._
  
  private val dirs: Array[String] = Array("North","South","East","West","Up","Down")
  private var exitActors: Array[Option[ActorRef]] = null
  
  def receive =
  {
    case LinkExits(roomMap) => exitActors = exits.map(key => roomMap.get(key))
    case GetDescription => sender ! Player.PrintMessage(description)
    case GetExit(dir) => sender ! Player.TakeExit(getExit(dir))
    case GetItem(itemName) => sender ! Player.TakeItem(getItem(itemName))
    case DropItem(item) => dropItem(item)
    case m => println("Room got an unhandled message: " + m)
  }
  
  def description(): String = 
  {
    val roomStr = name + "\n"
    val descStr = desc + "\n"
    val exitsStr = "Exits: " + exitsToString + "\n"
    var s = "None"
    if(items.length > 0 ) s = items.map(_.name).mkString(", ")
    val itemsStr = "Items: " + s + "\n"
    roomStr + descStr + exitsStr + itemsStr
  }
  
  def exitsToString(): String =
  {
    (for(i <- 0 until exits.length; if exits(i) != "-1") yield
    {
      dirs(i)
    }).mkString(", ")
  }

  def getExit(dir: Int): Option[ActorRef] =
  {
    exitActors(dir)
  }
  
  def getItem(itemName: String): Option[Item] =
  {
    val item = items.find(_.name.toLowerCase == itemName.toLowerCase)
    if(item == None) None
    else
    {
      items = items.filterNot(_.name.toLowerCase == itemName.toLowerCase)
      item
    }
  }
  
  def dropItem(item: Item): Unit =
  {
    items :+= item
  }
  
  
}

object Room
{
  case class LinkExits(roomMap: Map[String, ActorRef])
  case object GetDescription
  case class GetExit(dir: Int)
  case class GetItem(itemName: String)
  case class DropItem(item: Item)
  
}
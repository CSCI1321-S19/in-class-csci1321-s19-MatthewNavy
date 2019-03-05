package mud

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

class RoomManager extends Actor
{
  val rooms = readRooms()
  
  for(room <- context.children) room ! Room.LinkExits(rooms)
  
  def receive =
  {
    case m => println("RoomManager got an unhandled message: " + m)
  }
  
  def readRooms(): Map[String, ActorRef] =
  {
    val xmlData = scala.xml.XML.load("map.xml")
    val data = (xmlData \ "room").map(parseRoom).toMap
    data
  }
  
  def parseRoom(node: scala.xml.Node): (String, ActorRef) =
  {
    val name = (node \ "@name").text.trim
    val desc = (node \ "desc").text.trim
    val items = (node \ "item").toBuffer.map(parseItem)
    val key = (node \ "@key").text
    val exits = (node \ "@exitKeys").text.split(",").toArray
    val child = context.actorOf(Props(new Room(name, desc, items, exits)), key)
    (key, child)
  }
  
  def parseItem(node: scala.xml.Node): Item =
  {
    val name = (node \ "@name").text.trim
    val desc = node.text.trim
    Item(name, desc)
  }
}

object RoomManager
{
  
}
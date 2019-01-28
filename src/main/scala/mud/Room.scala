package mud

class Room(
    val name: String,
    val desc: String,
    val num: Int,
    private var items: List[Item],
    private val exits: Array[Int]) 
{
  def description(): String = 
  {
    ???
  }

  def getExit(dir: Int): Option[Room] =
  {
    if(exits(dir) < 0) None
    else Some(Room.rooms(exits(dir)))
  }
  
  def getItem(itemName: String): Option[Item] =
  {
    ???
  }
  
  def dropItem(item: Item): Unit =
  {
    ???
  }
  
}

object Room
{
  val rooms = readRooms()
  
  def main(args: Array[String]): Unit =
  {
    //rooms.foreach(a => println(a.name + a.desc + a.num + a.exits.mkString(",")))
    //rooms.foreach(a => println(a.items))
  }
  
  def readRooms(): Array[Room] =
  {
    val xmlData = scala.xml.XML.load("map.xml")
    val data = (xmlData \ "room").map(parseRoom).toArray
    data
  }
  
  def parseRoom(node: scala.xml.Node): Room =
  {
    val name = (node \ "@name").text
    val desc = (node \ "desc").text
    val num = (node \ "@num").text.toInt
    val items = (node \ "item").toList.map(parseItem)
    val exits = (node \ "@exits").text.split(",").map(_.toInt)
    new Room(name, desc, num, items, exits)
  }
  
  def parseItem(node: scala.xml.Node): Item =
  {
    val name = (node \ "@name").text
    val desc = node.text
    Item(name, desc)
  }
}
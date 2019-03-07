package actors

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatManager extends Actor
{
  import ChatManager._
  
  def receive =
  {
    case CheckAllInput => for(child <- context.children) child ! Chatter.CheckInput
    case NewChatter(sock, in, out, name) => context.actorOf(Props(new Chatter(sock, in, out, name)), name)
    case PrintToAll(message) => for(child <- context.children) child ! Chatter.PrintMessage(message)
    case m => println("ChatManager got an unhandled message: " + m)
  }
}

object ChatManager
{
  case object CheckAllInput
  case class NewChatter(sock: Socket, in: BufferedReader, out: PrintStream, name: String)
  case class PrintToAll(message: String)
}
package adt

class LinkedListQueue[A] extends MyQueue[A] 
{
  import LinkedListQueue.Node
  private var front: Node[A] = null
  private var back: Node[A] = null
  
  def dequeue(): A =
  {
    val ret = front.data
    front = front.next
    if(front == null) back = null
    ret
  }
  
  def enqueue(a: A): Unit =
  {
    val n = new Node[A](a, null)
    if(back == null) front = n
    else back.next = n
    back = n
  }
  
  def isEmpty: Boolean = front == null
  
  def peek: A = front.data
  
}

object LinkedListQueue
{
  private class Node[A](val data: A, var next: Node[A])
}
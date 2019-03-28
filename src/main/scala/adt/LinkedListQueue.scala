package adt

class LinkedListQueue[A] extends MyQueue[A] 
{
  import LinkedListQueue.Node
  private var front: Node[A] = null
  private var back: Node[A] = null
  
  def dequeue(): A = ???
  def enqueue(a: A): Unit = ???
  def isEmpty: Boolean = ???
  def peek: A = ???
}

object LinkedListQueue
{
  private case class Node[A](data: A, next: Node[A])
}
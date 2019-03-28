package adt

class LinkedListStack[A] extends MyStack[A] 
{
  import LinkedListStack.Node
  
  private var top: Node[A] = null
  
  def isEmpty: Boolean = top == null
  def peek(): A = top.data
  def pop(): A =
  {
    val ret = top.data
    top = top.next
    ret
  }
  def push(elem: A): Unit = top = new Node(elem, top)
  
  
}

object LinkedListStack
{
  private case class Node[A](data: A, next: Node[A])
}
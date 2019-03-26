package adt

import scala.collection.mutable

class SLList[A] extends mutable.Buffer[A]
{
  private class Node(var data: A, var next: Node)
  private var hd: Node = null
  private var tl: Node = null
  private var _length = 0
  
  
  def +=(elem: A): this.type = 
  {
    val node = new Node(elem, null)
    if(hd == null) hd = node
    else tl.next = node
    tl = node
    _length += 1
    this
  }
  def +=:(elem: A): this.type = ???
  def apply(n: Int): A = 
  {
    var rover = hd
    for(i <- 0 until n) rover = rover.next
    rover.data
  }
  def clear(): Unit = hd = null; tl = null; _length = 0 //for(i <- 0 until length) remove(i)
  def insertAll(n: Int,elems: Traversable[A]): Unit = ???
  def length: Int = _length
  def remove(n: Int): A = 
  {
    var rover = hd
    for(i <- 0 until n) rover = rover.next
    val ret = rover.next.data
    rover.next = rover.next.next
    if(rover.next == null) tl = rover
    _length -= 1
    ret
  }
  def update(n: Int,newelem: A): Unit = 
  {
    var rover = hd
    for(i <- 0 until n) rover = rover.next
    val node = new Node(newelem, rover.next)
    rover = node
    _length += 1
  }
  
  def iterator: Iterator[A] = ???
}
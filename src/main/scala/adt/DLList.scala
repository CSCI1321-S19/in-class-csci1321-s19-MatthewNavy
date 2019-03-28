package adt

import scala.collection.mutable

class DLList[A] extends mutable.Buffer[A]
{
  import DLList.Node
  private val end = new Node[A](null.asInstanceOf[A], null, null)
  end.next = end
  end.prev = end
  private var _length = 0
  
  def +=(elem: A): this.type = 
  {
    val node = new Node[A](elem, end.prev, end)
    end.prev.next = node
    end.prev = node
    _length += 1
    this
  }
  def +=:(elem: A): this.type = ???
  def apply(n: Int): A = 
  {
    var rover = end.next
    for(i <- 0 until n) rover = rover.next
    rover.data
  }
  def clear(): Unit = end.prev = end; end.next = end; _length = 0 //for(i <- 0 until length) remove(i)
  def insertAll(n: Int,elems: Traversable[A]): Unit = ???
  def length: Int = _length
  def remove(n: Int): A = 
  {
    require(n >= 0 && n < length)
    _length -= 1
    var rover = end.next
    for(i <- 0 until n) rover = rover.next
    val ret = rover.data
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    ret
  }
  def update(n: Int,newelem: A): Unit = 
  {
    require(n >= 0 && n < length)
    var rover = end.next
    for(i <- 0 until n) rover = rover.next
    rover.data = newelem
  }
  
  def iterator: Iterator[A] =
  {
    new Iterator[A]
    {
      var rover = end.next
      def hasNext: Boolean = rover != end
      def next(): A =
      {
        val ret = rover.data
        rover = rover.next
        ret
      }
    }
  }
}

object DLList
{
  private class Node[A](var data: A, var prev: Node[A], var next: Node[A])
}
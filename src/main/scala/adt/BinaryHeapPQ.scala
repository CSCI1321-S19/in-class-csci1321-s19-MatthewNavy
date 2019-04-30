package adt

import scala.reflect.ClassTag

class BinaryHeapPQ[A: ClassTag](comp: (A, A) => Int) extends MyPriorityQueue[A]
{
  private var heap = new Array[A](10)
  private var end = 1
  
  def enqueue(a: A): Unit =
  {
    if(end >= heap.length)
    {
      val temp = new Array[A](heap.length * 2)
      Array.copy(heap, 0, temp, 0, heap.length)
      heap = temp
    }
    var bubble = end
    while(bubble > 1 && comp(a, heap(bubble / 2)) > 0)
    {
      heap(bubble) = heap(bubble / 2)
      bubble /= 2
    }
    heap(bubble) = a
    end += 1
  }
  
  def dequeue(): A =
  {
    val ret = heap(1)
    end -= 1
    val temp = heap(end)
    heap(end) = heap(0)
    var stone = 1
    var flag = true
    while(stone * 2 < end && flag)
    {
      var greater = if(stone * 2 < end && comp(heap(stone * 2 + 1), heap(stone * 2)) > 0) stone * 2 + 1 else stone * 2
      if(comp(heap(greater), temp) > 0)
      {
        heap(stone) = heap(greater)
        stone = greater
      }
      else flag = false
    }
    heap(stone) = temp
    ret
  }
  
  def peek: A = heap(1)
  
  def isEmpty: Boolean = end == 1
}
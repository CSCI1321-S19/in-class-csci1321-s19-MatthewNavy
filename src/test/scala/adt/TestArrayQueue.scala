package adt

import org.junit.Test
import org.junit.Assert._

class TestArrayQueue 
{
  @Test def testEmptyOnCreate(): Unit =
  {
    val q = new ArrayQueue[Int]
    assertTrue(q.isEmpty)
  }
  
  @Test def testEnqueueDequeue(): Unit =
  {
    val q = new ArrayQueue[Int]
    q.enqueue(1)
    assertFalse(q.isEmpty)
    assertEquals(1, q.peek)
    assertEquals(1, q.dequeue())
    assertTrue(q.isEmpty)
  }
  
  @Test def testEnqueueDequeueEnqueue(): Unit =
  {
    val q = new ArrayQueue[Int]
    val nums1 = Array(1,2,3)
    for(n <- nums1) q.enqueue(n)
    for(n <- nums1) assertEquals(n, q.dequeue)
  }
}
package adt

import org.junit._
import org.junit.Assert._

class TestSLList 
{
  var list: SLList[Int] = null
  
  @Before
  def init()
  {
    list = new SLList[Int]()
  }
  
  @Test
  def emptyOnCreate()
  {
    assertTrue(list.isEmpty)
  }
  
  // TODO: write more tests
}
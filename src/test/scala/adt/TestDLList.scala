package adt

import org.junit._
import org.junit.Assert._

class TestDLList 
{
  var list: DLList[Int] = null
  
  @Before
  def init()
  {
    list = new DLList[Int]()
  }
  
  @Test
  def emptyOnCreate()
  {
    assertTrue(list.isEmpty)
  }
  
  // TODO: write more tests
}
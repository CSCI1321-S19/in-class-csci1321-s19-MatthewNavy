package basics

object Sorts extends App
{
  def bubbleSort[A](arr: Array[A])(cmp: (A, A) => Boolean): Unit =
  {
    for(i <- 0 until arr.length - 1; j <- 0 until arr.length - 1 - i)
    {
      if(cmp(arr(j),arr(j+1)))
      {
        val temp = arr(j)
        arr(j) = arr(j+1)
        arr(j+1) = temp
      }
    }
  }
  
  
  val nums = Array.fill(10)(math.random)
  println(nums.mkString(" "))
  bubbleSort(nums)(_>_)
  println(nums.mkString(" "))
  
  val strs = Array("a","B","Cd","eF")
  println(strs.mkString(" "))
  bubbleSort(strs)(_<_)
  println(strs.mkString(" "))

}
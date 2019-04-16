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
  
  def quickSort[A](lst: List[A])(cmp: (A, A) => Boolean): List[A] = lst match
  {
    case Nil => Nil
    case h :: Nil => lst
    case pivot :: t =>
      val (less, greater) = t.partition(a => cmp(a, pivot))
      quickSort(less)(cmp) ::: (pivot :: quickSort(greater)(cmp))
  }
  
  def quickSort[A](arr: Array[A])(cmp: (A, A) => Boolean): Unit =
  {
    def helper(start: Int, end: Int): Unit = {
      if(end-start > 1) {
        val p = start + util.Random.nextInt(end-start)
        val tmp = arr(p)
        arr(p) = arr(start)
        arr(start) = tmp
        var low = start+1
        var high = end-1
        while(low <= high) {
          if(cmp(arr(low), arr(start))) {
            low += 1
          } else {
            val tmp = arr(low)
            arr(low) = arr(high)
            arr(high) = tmp
            high -= 1
          }
        }
        val tmp2 = arr(start)
        arr(start) = arr(high)
        arr(high) = tmp2
        helper(start, high)
        helper(low, end)
      }
    }
    helper(0, arr.length)
  }
  
  println("BubbleSort:")
  val nums = Array.fill(10)(math.random)
  println(nums.mkString(" "))
  bubbleSort(nums)(_ > _)
  println(nums.mkString(" "))
  
  println("BubbleSort:")
  val strs = Array("a","B","Cd","eF")
  println(strs.mkString(" "))
  bubbleSort(strs)(_ < _)
  println(strs.mkString(" "))

  println("QuickSort (out of place):")
  val nums2 = List.fill(10)(math.random)
  println(nums2.mkString(" "))
  println(quickSort(nums2)(_ < _).mkString(" "))
  
  println("QuickSort (in place):")
  val nums3 = Array.fill(10)(math.random)
  println(nums3.mkString(" "))
  quickSort(nums3)(_ < _)
  println(nums3.mkString(" "))
}
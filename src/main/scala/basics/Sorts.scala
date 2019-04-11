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
    ???
  }
  
  
  val nums = Array.fill(10)(math.random)
  println(nums.mkString(" "))
  bubbleSort(nums)(_ > _)
  println(nums.mkString(" "))
  
  val strs = Array("a","B","Cd","eF")
  println(strs.mkString(" "))
  bubbleSort(strs)(_ < _)
  println(strs.mkString(" "))

  
  val nums2 = List.fill(10)(math.random)
  println(nums2.mkString(" "))
  println(quickSort(nums2)(_ < _).mkString(" "))
}
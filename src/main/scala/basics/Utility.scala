package basics

object Utility extends App
{
  def findAndRemove[A](list: List[A])(pred: A => Boolean): (Option[A], List[A]) =
  {
    list match
    {
      case Nil => (None, list)
      case h :: t => 
        if(pred(h)) (Some(h), t) else
        {
          val (found, fixedList) = findAndRemove(t)(pred)
          (found, h :: fixedList)
        }
    }
  }
  
  
  val nums = List(1,2,3,4,5,6,7,8,9,5,5,5,5)
  val (five, notFive) = findAndRemove(nums)(_ == 5)
  println(five, notFive)
}
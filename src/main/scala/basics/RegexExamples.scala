package basics

object RegexExamples extends App 
{
  val NumberedLine = """(\d+)\.(.*)""".r
  def numberedLines(list: List[String]): Map[Int, String] =
  {
    (for(NumberedLine(num, word) <- list) yield num.toInt -> word).toMap
  }
  
  println(numberedLines(List("1. first", "garbage", "2. second")))
}
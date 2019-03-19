package puyo

class Jelly(val x: Int, val y: Int) extends Blob
{
  def color = PuyoColor.Gray
  
  def move(dx: Int, dy: Int): Jelly =
  {
    new Jelly(dx, dy)
  }
  
}
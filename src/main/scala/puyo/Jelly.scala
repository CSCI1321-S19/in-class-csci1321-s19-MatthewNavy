package puyo

class Jelly(val x: Int, val y: Int) extends Blob
{
  def color = PuyoColor.Gray
  
  def fall(): Jelly =
  {
    new Jelly(x, y+1)
  }
}
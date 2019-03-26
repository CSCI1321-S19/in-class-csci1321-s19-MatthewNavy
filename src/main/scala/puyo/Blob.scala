package puyo

trait Blob 
{
  def x: Int
  def y: Int
  def color: PuyoColor.Value
  
  def move(dx: Int, dy: Int): Blob
  
  def makePassable: PassableBlob = PassableBlob(x, y, color)
}
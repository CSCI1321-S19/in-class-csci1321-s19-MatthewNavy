package puyo

trait Blob 
{
  def x: Int
  def y: Int
  def color: PuyoColor.Value
  
  def fall(): Blob
  {
    
  }
}
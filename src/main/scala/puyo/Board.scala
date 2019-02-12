package puyo

class Board 
{
  private var _blobs = List.tabulate[Blob](6)(i => new Jelly(i, 11))
  private var _current = new Twoyo(new Puyo(2, -1, PuyoColor.random()), 
      new Puyo(2, 0, PuyoColor.random()))
  private var _next = new Twoyo(new Puyo(6, 0, PuyoColor.random()), 
      new Puyo(6, 1, PuyoColor.random()))
  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false
  
  
  def blobs = _blobs
  def current = _current
  def next = _next
  
  private val fallInterval = 1.0
  private var fallDelay = 0.0
  private val moveInterval = 0.1
  private var moveDelay = 0.0
  
  def update(delay: Double): Unit =
  {
    fallDelay += delay
    moveDelay += delay
    if(moveDelay > moveInterval)
    {
      if(leftHeld) _current = current.move(-1, 0, boardClear)
      if(rightHeld) _current = current.move(1, 0, boardClear)
    //if(upHeld) _current = current.move(0, -1)
      if(downHeld) _current = current.move(0, 1, boardClear)
      moveDelay = 0.0
    }
    if(fallDelay > fallInterval)
    {
      if(current.isClear(0, 1, boardClear)) _current = current.move(0, 1, boardClear)
      else 
      {  
        _blobs ::= current.p1
        _blobs ::= current.p2
        _current = next
        _next = new Twoyo(new Puyo(2, -1, PuyoColor.random()), 
                new Puyo(2, 0, PuyoColor.random()))
      }
      fallDelay = 0.0
    }
  }
  
  def boardClear(x: Int, y: Int): Boolean =
  {
    blobs.forall(b => b.x != x || b.y != y) 
  }
  
  //def upPressed() = upHeld = true
  def downPressed() = downHeld = true
  def leftPressed() = leftHeld = true
  def rightPressed() = rightHeld = true
  
  //def upReleased() = upHeld = false
  def downReleased() = downHeld = false
  def leftReleased() = leftHeld = false
  def rightReleased() = rightHeld = false
  
}


object Board
{
  val Width = 6
  val Height = 12
  
}
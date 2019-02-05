package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) 
{
  import Renderer._
  
  def render(board: Board): Unit =
  {
    gc.fill = Color.DarkGreen
    gc.fillRect(0, 0, Main.canvasWidth, Main.canvasHeight)
    
    for(blob <- board.blobs)
    {
      drawBlob(blob)
    }
    drawBlob(board.current.p1)
    drawBlob(board.current.p2)
  }
  
  def drawBlob(blob: Blob): Unit =
  {
    blob.color match
    {
      case PuyoColor.Red => gc.fill = Color.Red
      case PuyoColor.Yellow => gc.fill = Color.Yellow
      case PuyoColor.Blue => gc.fill = Color.Blue
      case PuyoColor.Green => gc.fill = Color.Green
      case PuyoColor.Pink => gc.fill = Color.Pink       
      case PuyoColor.Gray => gc.fill = Color.Gray
    }
      gc.fillOval(blob.x * cellSize, blob.y * cellSize, cellSize, cellSize)
  }
}

object Renderer
{
  val cellSize = 30
}
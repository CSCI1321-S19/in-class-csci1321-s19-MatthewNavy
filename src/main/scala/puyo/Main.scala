package puyo

import scalafx.application.JFXApp
import scalafx.scene.canvas._
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.Includes._
import scalafx.scene.input.KeyCode

object Main extends JFXApp
{
  val canvasWidth = Board.Width * Renderer.cellSize
  val canvasHeight = Board.Height * Renderer.cellSize
  stage = new JFXApp.PrimaryStage
  {
    title = "Puyo"
    scene = new Scene(canvasWidth, canvasHeight)
    {
      val canvas = new Canvas(canvasWidth, canvasHeight)
      val gc = canvas.graphicsContext2D
      val renderer = new Renderer(gc)
      val board = new Board()
      
      content = canvas
      
      onKeyPressed = (ke: KeyEvent) =>
      {
        ke.code match
        {
          //case KeyCode.W|KeyCode.Up => board.upPressed()
          case KeyCode.S|KeyCode.Down => board.downPressed()
          case KeyCode.A|KeyCode.Left => board.leftPressed()
          case KeyCode.D|KeyCode.Right => board.rightPressed()
          case _ =>
        }
      }
      
      onKeyReleased = (ke: KeyEvent) =>
      {
        ke.code match
        {
          //case KeyCode.W|KeyCode.Up => board.upReleased()
          case KeyCode.S|KeyCode.Down => board.downReleased()
          case KeyCode.A|KeyCode.Left => board.leftReleased()
          case KeyCode.D|KeyCode.Right => board.rightReleased()
          case _ =>
        }
      }
      
      var lastTime = -1L
      val timer: AnimationTimer = AnimationTimer(time =>
      {
         if(lastTime != -1)
         {
           val delay = (time - lastTime) / 1e9
           board.update(delay)
           renderer.render(board)
         }
         lastTime = time
      })
      timer.start()
      
      
    }
  }
}
package puyo

import scalafx.application.JFXApp
import scalafx.scene.canvas._
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer

object Main extends JFXApp
{
  val canvasWidth = 6 * Renderer.cellSize
  val canvasHeight = 12 * Renderer.cellSize
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
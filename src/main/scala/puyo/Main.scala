package puyo

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext

object Main extends JFXApp
{
  val canvasWidth = 6 * 30
  val canvasHeight = 12 * 30
  stage = new JFXApp.PrimaryStage
  {
    title = "Puyo"
    scene = new Scene(canvasWidth, canvasHeight)
    {
      val canvas = new Canvas(canvasWidth, canvasHeight)
      val gc = canvas.getGraphicsContext2D
      val renderer = new Renderer(gc)
      val board = new Board()
      
      content = canvas
      
      renderer.render(board)
    }
  }
}
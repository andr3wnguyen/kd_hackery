
import Classes.{Game, Hungman, Word}
import scala.util.Random
import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.swing.Swing.LineBorder

//drawing
import java.awt.image.BufferedImage
import java.awt.{Graphics2D,Font,BasicStroke}
import java.awt.geom._


object RunWithGui extends SimpleSwingApplication {
  var randomLength = new Random().between(4, 9)
  var starterWord = "_" * randomLength
  var word = new Word(starterWord)
  var game = new Game(word)
  var hungman = new Hungman()
  def newGame():Unit = {
    randomLength = new Random().between(4, 9)
    starterWord = "_" * randomLength
    word = new Word(starterWord)
    game = new Game(word)
    }
  def top = new MainFrame() {
    def draw(attemptsLeft:Int):Unit = {
      if(attemptsLeft<7){hangman.noose.text = "  ||"}
      if(attemptsLeft<6){hangman.head.text = " O"}
      if(attemptsLeft<5){hangman.arms.text = """/|\"""}
      if(attemptsLeft<4){hangman.torso2.text = "  |"}
      if(attemptsLeft<3){hangman.legs.text = """/"""}
      if(attemptsLeft<2){hangman.legs.text = """ / \"""}
      if(attemptsLeft<1){hangman.legs.text = """ / \ *dies*"""}
    }
    title = "Sneaky Hangman"

    val container = new BorderPanel
    container.preferredSize = new Dimension(1000, 800)
    val appTitle = new Label {
      text = "Sneaky Hangman"
      preferredSize = new Dimension(200, 50)
      border = LineBorder(Color.black)
    }
    container.layout(appTitle) = BorderPanel.Position.North

    //create container for the letters
    //new button with reactions added to the box, the button prints text
    val alphabet = Seq('a' to 'z')
    val letterChoices = new GridPanel(10, 20) {
      for (i <- alphabet; j <- i) {
        contents += new Button {
          text = j.toString
          reactions += {
            case ButtonClicked(_) =>
              draw(game.attemptsLeft)
              if (game.attemptCheck && game.gameStatus) {
                game.guess(Option(text.head))
                currentBoard.currentBoardLabel.text = game.currentBoard
                enabled = false
                if(game.attemptsLeft==0) {currentBoard.gameStatusLabel.text = "Last try!"}
                else {currentBoard.gameStatusLabel.text = s"${game.attemptsLeft+1} tries left."}
                if (!game.currentBoard.contains("_")) {
                  currentBoard.gameStatusLabel.text = "You win!"
                }
              }
                else {
                  currentBoard.gameStatusLabel.text = "You lose."
                  currentBoard.currentGuessLabel.text = s"The word was '${word.answer}'."
                }
              }
          }
        }
        contents += new Button {
          text = "New Game"
          reactions += {
            case ButtonClicked(_) => {
                  //reset the buttons
            contents.foreach {
              case button:Button if !button.enabled => button.enabled = true
              case _ => }
            //reset the game
            newGame()
            //reset the board
            currentBoard.currentBoardLabel.text = game.currentBoard
            currentBoard.gameStatusLabel.text = s"${game.attemptsLeft+1} tries left."
            currentBoard.currentGuessLabel.text = ""
              //reset the hangman
            hangman.noose.text = ""
            hangman.head.text = ""
            hangman.torso1.text = ""
            hangman.arms.text = ""
            hangman.torso2.text = ""
            hangman.legs.text = ""
            }
            }
          }
        }

    //add letters to container
    container.layout(letterChoices) = BorderPanel.Position.South


    val hangman = new BoxPanel(Orientation.Vertical) {
      val noose = new Label(""){xLayoutAlignment = 0.5; yLayoutAlignment = 0.5}
      val head = new Label(""){xLayoutAlignment = 0.5; yLayoutAlignment = 0.5}
      val torso1 = new Label {text =""}
      val torso2 = new Label {text =""}
      val arms = new Label {text =""}
      val legs = new Label {text =""}
      contents += noose
      contents += head
      contents += torso1
      contents += arms
      contents += torso2
      contents += legs
      font = new Font("Courier New",java.awt.Font.BOLD, 4000)
    }
    hangman.preferredSize = new Dimension(10, 10)
    container.layout(hangman) = BorderPanel.Position.Center





    val currentBoard = new BorderPanel {
      val currentBoardLabel = new Label {
        text = game.currentBoard
        font = new Font("Courier New",java.awt.Font.PLAIN , 50)
      }
      val gameStatusLabel = new Label {
        text = ""
        font = new Font("Courier New",java.awt.Font.PLAIN , 25)
      }
      val currentGuessLabel = new Label {
        text = ""
        font = new Font("Courier New",java.awt.Font.PLAIN , 10)
      }
      preferredSize = new Dimension(400, 200)

      layout(currentBoardLabel) = BorderPanel.Position.Center
      layout(gameStatusLabel) = BorderPanel.Position.North
      layout(currentGuessLabel) = BorderPanel.Position.South
    }
    container.layout(currentBoard) = BorderPanel.Position.East
    contents = container

  }
}



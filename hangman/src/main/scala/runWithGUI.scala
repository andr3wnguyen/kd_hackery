
import Classes.{Game, Hungman, Word}
import scala.util.Random
import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.swing.Swing.LineBorder



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
    hungman = new Hungman()
    }
  def top = new MainFrame() {
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
              if (game.attemptCheck && game.gameStatus) {
                game.guess(Option(text.head))
                currentBoard.currentBoardLabel.text = game.currentBoard
                enabled = false
                currentBoard.gameStatusLabel.text = s"${game.attemptsLeft+1} tries left."
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
            }
            }
          }
        }

    //add letters to container
    container.layout(letterChoices) = BorderPanel.Position.South


    val hangman = new BoxPanel(Orientation.Horizontal) {
      val hangmanLabel = new Label {
        text = "this is where the hangman diagram is implemented"
      }
      contents += hangmanLabel
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



import Classes.{Game, Hungman, Word}

import scala.util.Random

object run extends App {

  println("Sneaky hangman")

  val randomLength = new Random().between(4,9)
  val starterWord = "_"*randomLength
  println(s"Your word has ${starterWord.length} letters")
  val word = new Word(starterWord)
  val game = new Game(word)
  val hungman = new Hungman()

  while(game.gameStatus && game.attemptCheck) {
    println("\n" + game.currentBoard)

    var userInput: Option[Char] = None
    while (userInput.isEmpty) {
      val input = scala.io.StdIn.readLine("Enter your guess: ").trim
      if (input.length == 1 && input.head.isLetter) {
        userInput = Some(input.head)
      } else {
        println("Please only enter a single letter.")
      }
    }

    game.guess(userInput)
    if(game.attemptsLeft<7){hungman.draw(game.attemptsLeft)}

  }


}

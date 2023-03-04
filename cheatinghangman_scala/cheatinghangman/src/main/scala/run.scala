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
    //userGuess needs to be made into only accepting 1 letter
    var a: Option[Char] = None
    while (a.isEmpty) {
      val input = scala.io.StdIn.readLine("Enter your guess: ").trim
      if (input.length == 1 && input.head.isLetter) {
        a = Some(input.head)
      } else {
        println("Invalid input. Please enter a single letter.")
      }
    }

    game.guess(a)
    if(game.attemptsLeft<7){hungman.draw(game.attemptsLeft)}

  }


}

package Classes

class Game(word: Word) {
  var attemptsLeft = 6
  var guesses = Array.empty[Char]
  var currentBoard = word.answer.map(x => if ((guesses).contains(x)) x else "_").mkString("")

  //checks if game attempts still ok (used for while loop)
  def attemptCheck: Boolean =
    if (attemptsLeft == 0) {
      println(s"The word was ${word.answer}")
        false
    } else
        true

  //checks board still unwon - used for while loop)
  def gameStatus: Boolean =
    if (!currentBoard.contains("_")) {
      println("You win.")
        false
    } else
        true

  def guess(letter: Option[Char]): Unit = {
    if (!word.answer.contains(letter.get)) {
      guesses = letter.get +: guesses
      attemptsLeft -= 1
      println(s"${letter.get} was not in the word")
    } else {
      guesses = letter.get +: guesses
      println(s"${letter.get} was found in the word!")
      updateCurrentBoard()
      word.changeWord(currentBoard)
    }
    println(s"Guesses: [${guesses.mkString(", ")}]")

  }

  def updateCurrentBoard(): Unit = {
    currentBoard = word.answer.map(x => if (guesses.contains(x)) x else "_").mkString("")
  }
}

class Checker(word:Word) {
  val wordToCompare = word.getWord.toUpperCase
  val lettersToGuess = wordToCompare.toArray
  var status = true
  //prints the word
  //if the character is correct place colour it green
  //if the character is incorrect colour it red
  //if the character is correct but wrong place - colour it yellow

  private def correctLetter(letter:Char):String = {
    s"${Console.YELLOW_B}$letter${Console.RESET}"
  }

  private def incorrectLetter(letter:Char): String = {
    s"${Console.RED_B}$letter${Console.RESET}"
  }

  private def correctLetterAndPosition(letter:Char): String = {
    s"${Console.GREEN_B}$letter${Console.RESET}"
  }

  def checkStatus(guess:String): Unit = {
    if(guess.toUpperCase == wordToCompare) status = false
  }

//colours correct letters green (contains word GREEN) if not then leaves normal
  private def currentWordCorrectLettersOnly(guess:String): Seq[String] = {
    val listOfZippedChars = guess.toUpperCase.toArray.zip(lettersToGuess)
    listOfZippedChars.map(char =>
      if(char._1 == char._2) char._1.toString
      else
        "_")
  }

//used in generateColouredString to compare if elemnt is in string in wrong position, returns Seq of strings
    private def getUnrevealedLetters(guess:String): Seq[String] = {
    val currentWord = currentWordCorrectLettersOnly(guess.toUpperCase)
    //changes incorrect letters to _
    val listOfIndexes = currentWord.zipWithIndex.filter(pair => pair._1.contains("_")).map(pair => pair._2)
    listOfIndexes.map(index => lettersToGuess(index).toString)
  }

//creates new string
//  def generateColouredString(guess: String): String = {
//      val unrevealedCorrectLetters = getUnrevealedLetters(guess.toUpperCase)
//      val listOfZippedChars = guess.toUpperCase.toArray.zip(lettersToGuess)
//      listOfZippedChars.map(char =>
//        if (char._1 == char._2) correctLetterAndPosition(char._1)
//        else {
//          if (unrevealedCorrectLetters.contains(char._1.toString)) correctLetter(char._1)
//          else
//            incorrectLetter(char._1)
//        }
//      ).mkString("")
//    }


  def generateColouredArray(guess: String): Array[String] = {
    val unrevealedCorrectLetters = getUnrevealedLetters(guess.toUpperCase)
    val listOfZippedChars = guess.toUpperCase.toArray.zip(lettersToGuess)
    listOfZippedChars.map(char =>
      if (char._1 == char._2) correctLetterAndPosition(char._1.toUpper)
      else {
        if (unrevealedCorrectLetters.contains(char._1.toString)) correctLetter(char._1.toUpper)
        else
          incorrectLetter(char._1.toUpper)
      }
    )
  }

  def apply(guess:String): Array[String] = {
    checkStatus(guess)
    generateColouredArray(guess)
  }




}




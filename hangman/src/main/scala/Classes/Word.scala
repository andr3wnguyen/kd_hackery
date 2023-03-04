package Classes

import scala.io.Source
import scala.util.Random


//word needs to be initiated by giving a number - this is then converted to ____ equivilanet and passed to word
class Word(currentBoard: String) {
  //convert the int to a word to pass into the object (e.g "_"*Int) and pass this into a method
  var listOfWords = filterList(currentBoard)
  var answer = setAnswer(listOfWords)

  // method takes the given length and returns a string
  // checks the string
  private def setAnswer(availableAnswers: Seq[String]): String = {
    val random = new Random().nextInt(availableAnswers.length)
    val newList = for {word <- availableAnswers} yield word
    newList(random)
  }

  //checks if words can potentially be used for sneaky things
  private def wordChecker(word: String, currentGuess: String): Boolean = {
    val zippedWord = word.zip(currentGuess)
    //matches each letter to the guess, with _ in the currentGuess being wildcxards
    val matchedLetters = for (i <- zippedWord) yield i._1 == i._2 || i._2.toString == "_"
    matchedLetters.forall(_ == true)
  }


  private def revealedLetters(currentGuess: String): String = {
    var distinctRevealedLetters = ""
    //get the letters from the guess and add to a list
    for (i <- currentGuess) {
      if (i.toString != "_") {
        distinctRevealedLetters += i.toString
      }
    }
    distinctRevealedLetters
  }

  private def compareRevealedLetters(currentGuess: String, word: String): Boolean = {
    val currentString = revealedLetters(currentGuess)
    val commonCount = for (i <- currentString) yield word.count(_ == i) == currentString.count(_ == i)
    commonCount.forall(_ == true)
  }


  //returns  new seq that can be passed into setAnswer
  //currentGuess is the current state of the answer ("__a__" etc.)
  private def filterList(currentGuess: String): Seq[String] = {
    val allWords = Source.fromResource("words.txt").getLines.toList
    for {
      i <- allWords
      if i.length == currentGuess.length && wordChecker(i, currentGuess) && compareRevealedLetters(currentGuess, i)
    } yield i
  }

  def changeWord(updatedCurrentBoard: String): Unit = {
    val availableWords = filterList(updatedCurrentBoard)
    if(availableWords.length==1){
      answer = setAnswer(availableWords)
    } else { answer = setAnswer(availableWords.filter(_ != currentBoard))
    }
  }


}
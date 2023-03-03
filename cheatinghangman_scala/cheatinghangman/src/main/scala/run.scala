import scala.+:
import scala.io.Source
import scala.util.Random

//each word will be a randoms string from a filtered list
object run extends App {

  println("Sneaky hangman")

  val randomLength = new Random().between(3,5)
  val starterWord = "_"*randomLength
  println(s"Your word has ${starterWord.length} letters ${starterWord} ")
  val word = new Word(starterWord)

  val game = new Game(word)

  while(game.gameStatus && game.attemptCheck) {
    println(game.currentBoard)
    println("Enter a guess")
    val userGuess = scala.io.StdIn.readLine()
    game.guess(userGuess)


  }












    //word needs to be initiated by giving a number - this is then converted to ____ equivilanet and passed to word
  class Word(currentBoard: String) {
    //convert the int to a word to pass into the object (e.g "_"*Int) and pass this into a method
    var listOfWords = filterList(currentBoard)
    var answer = setAnswer(listOfWords)

    // method takes the given length and returns a string
    // checks the string
    private def setAnswer(availableAnswers:Seq[String]): String = {
      val random = new Random().nextInt(availableAnswers.length)
      val newList = for {word <- availableAnswers} yield word
      newList(random)
    }

    //checks if words can potentially be used for sneaky things
    private def wordChecker(word:String, currentGuess:String):Boolean = {
      val zippedWord = word.zip(currentGuess)
      //matches each letter to the guess, with _ in the currentGuess being wildcxards
      val matchedLetters = for(i<-zippedWord) yield i._1 == i._2 || i._2.toString == "_"
      matchedLetters.forall(_ == true)
    }


      private def revealedLetters(currentGuess:String): String = {
        var distinctRevealedLetters = ""
        //get the letters from the guess and add to a list
        for(i<-currentGuess){
          if(i.toString != "_"){
            distinctRevealedLetters += i.toString
          }
        }
        distinctRevealedLetters
      }
      private def compareRevealedLetters(currentGuess:String, word:String): Boolean = {
        val currentString = revealedLetters(currentGuess)
        val commonCount = for(i<-currentString) yield word.count(_ == i) == currentString.count(_== i)
        commonCount.forall(_ == true)
      }




    //returns  new seq that can be passed into setAnswer
    //currentGuess is the current state of the answer ("__a__" etc.)
    private def filterList(currentGuess:String): Seq[String] = {
      val allWords = Source.fromResource("words.txt").getLines.toList
      for {
        i <- allWords
        if i.length==currentGuess.length && wordChecker(i, currentGuess) && compareRevealedLetters(currentGuess,i)
      } yield i
    }
      def changeWord(updatedCurrentBoard:String): Unit = {
        answer = setAnswer(filterList(updatedCurrentBoard))
      }
    }
  //class Game takes a word object to initiate the game (word is a new object that is

  class Game(word:Word){
    var attemptsLeft = 11
    var guesses = Array.empty[String]
    var currentBoard = word.answer.map(x => if(guesses.contains(x.toString)) x.toString else "_").mkString("")


//checks if game attempts still ok (used for while loop)
    def attemptCheck: Boolean =
      if(attemptsLeft == 0){
        println("You lose.")
        false
      } else
        true

    //checks board still unwon - used for while loop)
    def gameStatus:Boolean =
      if(!currentBoard.contains("_")){
        println("You win.")
        false
      } else
          true

    def guess(letter:String): Unit = {
      if(!word.answer.contains(letter)){
        guesses = letter +: guesses
        attemptsLeft -= 1
        println(s"$letter was not in the word")
      } else {
        guesses = letter +: guesses
        println(s"$letter was found in the word!")
        updateCurrentBoard()
        word.changeWord(currentBoard)
      }
      println(currentBoard)
      print(word.answer)
    }

    def updateCurrentBoard(): Unit = {
        currentBoard = word.answer.map(x => if(guesses.contains(x.toString)) x.toString else "_").mkString("")
    }


  }




}
//TODO  change the word with each iteration
//edge cases like repeating words (wow) when it changes.
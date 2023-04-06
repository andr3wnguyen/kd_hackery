object run extends App {
  val printer = new Printer()
  var attempts = 5
  var test = Seq[String]()
  //  val gameMode = scala.io.StdIn.readLine("Choose your game mode [1/2/3]: \n [1] 5 letter words \n [2] Pokemon \n [*] Random length mode \n").trim.toInt
  val gameMode = scala.io.StdIn.readLine("Choose your game mode [1/2/3]: \n [1] 5 letter words \n [2] Pokemon \n [*] Random length mode \n") match {
    case "1" => 1
    case "2" => 2
    case _ => 3
  }
  {
    val word = new Word(gameMode)
    val checker = new Checker(word)
    if (gameMode == 2) {
      printer.printASCII(2)
      println(s"Your Pokemon has ${word.getWord.length} characters.")
    } else {
      printer.printASCII(1)
      println(s"Your word is ${word.getWord.length} characters.")
    }
    println(checker.wordToCompare)
    val wordLength = checker.wordToCompare.length

    while (attempts != 0 && checker.status == true) {
      val input = scala.io.StdIn.readLine("").trim
      if (input.length != wordLength) {
        println(s"The correct word is ${wordLength} letters long.")
      }
//      else {
//        if (!word.listOfWords.contains(input) && gameMode == 2) {
//          println("Not a Pokemon.")
//        }
//        else if (!word.listOfWords.contains(input)) {
//          println("Not a word.")
//        }
        else {
          val playerString = checker(input).mkString("")
          test = test :+ playerString
          for (i <- test) {
            println(i)
          }
          if (input.toUpperCase == word.getWord.toUpperCase) {
            println(s"You win! Tries: ${6 - attempts}")
          }
          attempts -= 1
          println(s"Tries left: $attempts.")
          if (attempts == 0) {
            println("Game over! You lose!")
            println(s"The word was ${word.getWord}")
          }
        }
      }
    }
  }


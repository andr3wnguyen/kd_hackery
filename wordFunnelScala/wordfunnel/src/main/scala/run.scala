object run extends App {

  //get a valid user input
  val userInput = scala.io.StdIn.readLine("Enter two words separated with a space, e.g. bacon acon")
  val splitInput = userInput.split(" ")
  val firstWord = new FirstWord(splitInput(0))
  val secondWord = new SecondWord(splitInput(1))
  val funnel = new Funnel

  //return true or false

  println(funnel(firstWord,secondWord))


  funnel.printAllPossibleWords(firstWord)
  //additional option 1 - all words

}

object run extends App {

  //get a valid user input
  val userInput = scala.io.StdIn.readLine("Enter two words separated with a space, e.g. boats oats \n")
  val splitInput = userInput.split(" ")
  val firstWord = new FirstWord(splitInput(0))
  val secondWord = new SecondWord(splitInput(1))
  val funnel = new Funnel

  //return true or false

  println("Does it funnel?: ", funnel(firstWord,secondWord))

  println("Here's a list of words that could have been funneled from your first word: \n")
  funnel.bonus(firstWord)
  //additional option 1 - all words

  val fives = new Fives

  val timeStart = System.nanoTime()
  val bonusTwo = fives.bonusFunnel
  val timeEnd = System.nanoTime()
  val seconds = (timeEnd-timeStart)/1000000000
  val minutes = seconds/60

  println(s"${bonusTwo.length} words found. This took:  ${seconds} seconds => ${minutes} minutes")
  bonusTwo.foreach(println)
}

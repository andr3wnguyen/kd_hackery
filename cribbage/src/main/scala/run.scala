object run extends App {
//takes an array and a faceup card

  //calculates and returns
  var seqOfCards = Seq[Card]()
  while(seqOfCards.length < 4){
  val dealtCard = scala.io.StdIn.readLine("Deal a card: e.g. 5h for 5 of hearts, As for ace of spades etc.\n")
    val value = dealtCard.toCharArray.toSeq(0).toString
    val suit = dealtCard.toCharArray.toSeq(1).toString
//    val suit = suitAcronym match {
//      case "h" => "hearts"
//      case "c" => "clubs"
//      case "s" => "spades"
//      case "d" => "diamonds"
//    }
    val card = Card(suit, value)
    seqOfCards = seqOfCards:+card
  }

  val faceUpCardPrompt = scala.io.StdIn.readLine("Deal a faceUpCard: e.g. 5h for 5 of hearts, As for ace of spades etc.\n")
  val faceUpCardValue = faceUpCardPrompt.toCharArray.toSeq(0).toString
  val faceUpCardSuit = faceUpCardPrompt.toCharArray.toSeq(1).toString
  val faceUpCard = Card(faceUpCardSuit,faceUpCardValue)

  val score = new Score(seqOfCards,faceUpCard)
  score.calculateScore()
  val handScore = score.getScore
  print(s"You have dealt:")
  seqOfCards.foreach(println)
  println(faceUpCard)
  println(s"your score is: ${handScore}")


}

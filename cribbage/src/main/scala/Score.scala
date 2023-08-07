class Score(arrayOfCards:Seq[Card], faceUpCard:Card) {
  var score = 0
  val arrayOfAllCards = arrayOfCards:+faceUpCard

  def getScore: Int =
    score

  //  def scoreFifteen(hand:Seq[Card]): Unit = {
  //    var orderedValues = Seq()
  //
  //    //takes hand, cycles through the seq, checks the values and does calculations, is it's fifteen, add 2 to the score.
  //    for(i<-hand){
  //      if(i.value.contains(Seq("K","Q","J")){
  //        orderedValues = orderedValues + 10
  //      }
  //    }
  //
  //  }
  def orderHandByValue(): Seq[Int] = {
    //order the cards by value
    var handByValue = arrayOfAllCards.map(card => card.value match {
      case "J" | "Q" | "K" => 10
      case "A" => 1
      case _ => card.value.toInt
    }
    )
    handByValue.sorted
  }


  def scoreRuns(): Unit = {
    //checks sequence, if it is consecutive then add a point

    val orderedSeq = orderHandByValue()
    var runCount = 0
    var bestRunCount = 0
    // ** REDUCE(val,stream,function) instead of for-loop // filter/map
    //for each index, check if the next value is +1,
    for (i <- 1 until orderedSeq.length) {
      if(orderedSeq(i - 1)+1 == orderedSeq(i)) {
        runCount = runCount + 1
        if(runCount >=2) bestRunCount = runCount
      }
      else runCount = 0


      }
      val scoreToAdd = bestRunCount match {
        case 2 => 3
        case 3 => 4
        case 4 => 5
        case _ => 0
    }
      score = score + scoreToAdd
  }

  def scorePairs(): Unit = {
    val orderedSeq = orderHandByValue()
    var pairs = 0
    for(i<-0 until orderedSeq.length - 1) {
      if(orderedSeq(i) == orderedSeq(i+1)) {
        pairs = pairs + 1
      }
    }
    val scoreForPairs = pairs match {
      case 0 => 0
      case 1 => 2
      case 2 => 6
      case 3 => 12
    }
    score = score + scoreForPairs
  }

  def scoreFlush(): Unit = {
    //check suits of original and of the faceupcard
    val listOfSuits = arrayOfCards.map(_.suit)
    if(listOfSuits.tail.forall(_ == listOfSuits.head) && listOfSuits.head == faceUpCard.suit){
      score = score + 5
    }
    else if(listOfSuits.tail.forall(_ == listOfSuits.head)) {
      score = score + 4
    }
    else
      score = score
  }

  def scoreNobs(): Unit = {
    //get suits of any jacks
    val jackSuits = arrayOfCards.filter(card => card.value == "J").map(_.suit)
    val faceupCardSuit = faceUpCard.suit
    if(jackSuits.contains(faceupCardSuit))
      score = score + 1
    else
      score = score
    }


  def calculateScore():Unit = {
    scoreRuns()
    scorePairs()
    scoreFlush()
    scoreNobs()
    }
  }


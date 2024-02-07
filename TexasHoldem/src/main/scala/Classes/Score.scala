package Classes

class Score(player: Player, table: Table) {

  //score class gets a score given a player and the table - highest score wins - maybe this should be abstracted out into another class ?
  private val playerCards: Seq[Card] = player.hand
  private val tableCards: Seq[Card] = table.table
  var currentHand: Seq[Card] = playerCards ++ tableCards
  var winCondition = ""
  //  val currentHandSuits = currentHand.map(_.givenSuit)
  //  val currentHandValues = currentHand.map(_.givenValue)
  val listOfScoreMethods: Seq[Seq[Classes.Card] => Boolean] = Seq(isRoyalFlush _, isStraightFlush _, isFourOfAKind _, isFullHouse _, isFlush _, isStraight _, isThreeOfaKind _, isTwoPair _, isAPair _) //list of all methods for scoring [WOAH PARTIAL FUNCTIONS]


  def getCurrentHandsScore: Int = {
    getHandWeighting(currentHand)
  }

  def parseHand(hand: Seq[Card]): Seq[Boolean] = {
    //this method gives a weighting to the hand - for the hand, parse the hand through a list of methods on the hand until true is hit, then return that value
    listOfScoreMethods.map(method => method(hand))
  }


  def getHandWeighting(hand: Seq[Card]): Int = {
    val listOfWinMethodNames = Seq("Royal Flush", "Straight Flush", "Four Of A Kind", "Full House", "Flush", "Straight", "Three Of A Kind", "Two Pair", "Pair") //list of all methods for scoring [WOAH PARTIAL FUNCTIONS]

    val results = parseHand(hand)
    //parse through to find 'true' - if it's not in the list of cards then will be -1
    //-20 because the weighting is done negatively (lower weighting is better hand)
    val initialWeighting = results.indexOf(true)
    if (initialWeighting != -1) {
      winCondition = listOfWinMethodNames(initialWeighting)
      initialWeighting - 20
    }
    else {
      val highCard = getHighestCardInHand(hand)
      val highCardValue = highCard match {
        case 14 => "A"
        case 13 => "K"
        case 12 => "Q"
        case 11 => "J"
        case _ => highCard
      }
      winCondition = s"High card ${highCardValue}."
      initialWeighting - highCard

    }
    //added 20 to this as a weighting so separation between highcard can be done in the same weighting.
    //if it is in the list of scores, it will return a weighting of 20 + index.

    //weighting for high card will be added to the initial weighting
  }


  //scoring methods
  //method to check if the value is sequential
  def isSequential(listOfCards: Seq[Card]): Boolean = {
    val listOfCardValues = listOfCards.map(_.givenValue)
    val deckSequence = Seq[String]("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    val equivalentValues = Map[String, Int](
      "2" -> 2,
      "3" -> 3,
      "4" -> 4,
      "5" -> 5,
      "6" -> 6,
      "7" -> 7,
      "8" -> 8,
      "9" -> 9,
      "10" -> 10,
      "J" -> 11,
      "Q" -> 12,
      "K" -> 13,
      "A" -> 14
    )
    val cardValuesToEquivalentValues = for {i <- listOfCardValues} yield (equivalentValues(i))
    //converts seq of values of cards into equivalent values (1-14)
    val orderedValueList = cardValuesToEquivalentValues.sorted
    //orders the list ascending

    val firstValue = orderedValueList.head
    //gets the head value of the sorted list of ints

    if (firstValue > 10) {
      false
    } else {
      //get a startingIndex value to see where the straight would start. this maps the value for the start of the straight
      orderedValueList == Range(orderedValueList.head, orderedValueList.last + 1).toSeq


      //      val startingIndex = deckSequence(firstValue - 1)
      //      //get index of the head value within the orderedCardValues list
      //      val potentialStraight = for {i <- startingIndex until (startingIndex + 5)} yield deckSequence(i - 1)
      //      //this gives a potential straight based on the first value/head value given (runs)
      //
      //      val orderedListOfCardValues = for {i <- orderedValueList} yield deckSequence(i - 1)
      //      //converts back to an orders Seq of card values to compare to the potentialStraight
      //      //i-1 because deckSequenceIndex starts at 0.
      //
      //      val matchedValuesList = for {i <- orderedListOfCardValues.zip(potentialStraight)} yield i._1 == i._2
      //      //zip potentialStraight values with the listOfOrderedCard values
      //
      //      matchedValuesList.forall(_ == true)
    }
  }

  //takes a list of suits and returns true if all is the same
  def isAllSameSuit(listOfCards: Seq[Card]): Boolean = {
    val head = listOfCards.map(_.givenSuit).head
    listOfCards.map(_.givenSuit).forall(_ == head)
  }

  //straightFlush checker
  def isStraightFlush(hand: Seq[Card]): Boolean = {
    //checks the cards for sequential
    isAllSameSuit(hand) && isSequential(hand)
  }

  //royalFlush checker
  def isRoyalFlush(hand: Seq[Card]): Boolean = {
    //firstCard in the sorted list of card values should be 10

    val equivalentValues = Map[String, Int](
      "2" -> 2,
      "3" -> 3,
      "4" -> 4,
      "5" -> 5,
      "6" -> 6,
      "7" -> 7,
      "8" -> 8,
      "9" -> 9,
      "10" -> 10,
      "J" -> 11,
      "Q" -> 12,
      "K" -> 13,
      "A" -> 14
    )
    val sortedListOfCardValues = hand.map(cardValue => equivalentValues(cardValue.givenValue)).sorted
    if (sortedListOfCardValues.head != 10) {
      false
    } else {
      isStraightFlush(hand)
    }
  }

  //checks if there is a 4 of same values within the hand. If first or second value does not have a count of 4 within
  //the hand then 4 of a kind is impossible
  def isFourOfAKind(hand: Seq[Card]): Boolean = {
    val cardValues = hand.map(_.givenValue)
    cardValues.count(_ == cardValues(0)) == 4 || cardValues.count(_ == cardValues(1)) == 4
  }

  def isThreeOfaKind(hand: Seq[Card]): Boolean = {
    val cardValues = hand.map(_.givenValue).groupBy(identity)
    val countsForValue = for {i <- cardValues} yield i._2.count(_ == i._1)
    countsForValue.toSeq.contains(3)
    //sift through the cards to see if there are 3 of a card, ucreates a list of card values, then groups by value.
    //creates another list with counts and then checks if this list contains 3

  }

  def isAPair(hand: Seq[Card]): Boolean = {
    val cardValues = hand.map(_.givenValue).groupBy(identity)
    val countsForValue = for {i <- cardValues} yield i._2.count(_ == i._1)
    countsForValue.toSeq.contains(2)
  }

  def isFullHouse(hand: Seq[Card]): Boolean = {
    //checks if there is a three of a kind and a pair
    isThreeOfaKind(hand) && isAPair(hand)
  }

  def isFlush(hand: Seq[Card]): Boolean = {
    isAllSameSuit(hand)
  }

  def isStraight(hand: Seq[Card]): Boolean = {
    //this needs to be checked after straightflush
    isSequential(hand)
  }

  def isTwoPair(hand: Seq[Card]): Boolean = {
    //recursive?
    val cardValues = hand.map(_.givenValue).groupBy(identity)
    val countsForValue = for {i <- cardValues} yield i._2.count(_ == i._1)
    countsForValue.toSeq.count(_ == 2) > 1
  }

  def getHighestCardInHand(hand: Seq[Card]): Int = {
    //gets the highest card in the hand and gives an inverse weighting
    val listOfCardValues = hand.map(_.givenValue)
    val equivalentWeightings = Map[String, Int](
      "2" -> 2,
      "3" -> 3,
      "4" -> 4,
      "5" -> 5,
      "6" -> 6,
      "7" -> 7,
      "8" -> 8,
      "9" -> 9,
      "10" -> 10,
      "J" -> 11,
      "Q" -> 12,
      "K" -> 13,
      "A" -> 14
    )
    listOfCardValues.map(value => equivalentWeightings(value)).max
  }
}

/**
 * Texas Hold 'em - part two
 *
 * This doesn't really require the previous week's work, but if you've got it then you can just slot it in there. This week we're going to try to classify poker hands. Poker has the following types of hands:
 *
 * x1Royal flush: A-K-Q-J-10, all the same suit
 * x2Straight flush: Five cards in a sequence, all in the same suit
 * x3Four of a kind: All four cards of the same rank
 * x4Full house: Three of a kind with a pair
 * x5Flush: Any five cards of the same suit, but not in a sequence
 * x6Straight: Five cards in a sequence, but not of the same suit
 * x7Three of a kind: Three cards of the same rank
 * 8Two pair: Two different pairs
 * x9Pair: Two cards of the same rank
 * 9High Card: When you haven't made any of the hands above, the highest card wins
 * Hands higher in the list beat hands lower down.
 *
 * If hands have the same 'type' (e.g., a pair vs a pair) then draws are resolved as follows:
 * If the hands are of different rank in the 'type' of the hand then the higher ranked hand wins. For example, 10-10-4-6-9 beats 9-9-4-6-8: a pair of tens beats a pair of nines, and the other cards (4-6-9 vs 4-6-8) are irrelevant because they aren't part of the pair. As another example, 10-9-8-7-6 beats 9-8-7-6-5: a straight where the high card is a ten is higher than a straight where the high card is a nine.
 * If the hands have equal rank in the 'type' (for example, if both hands have a pair of twos) then the cards which do not take part in determining the 'type' of the hand (called kickers) are checked. The hand with the highest kicker wins, and if they are equal then the next highest kickers are compared. If the next highest kickers are equal then the next are compared, and so on. For example, 10-10-4-6-8 beats 10-10-3-6-8: both have a pair of tens, but the former has 8-6-4 kickers which are higher ranked than 8-6-3 kickers.
 * There's a good summary on Wikipedia if you need more details and examples.
 * Write a function that takes two hands of five cards and tells you which hand is better. If you can handle draws and kickers then that's even better.
 *
 *
 * gets a score of the current hand
 *
 * use of a while loop (while return value != Int){do these methods until true} <- the methods will be the bools for line 8-18
 * goes through the ranked methods and breaks at a point where true and assigns this score to the hand, then compares hands
 * 1) write the methods for true/false for each one and return true
 * 2) score assignment? hand score needs to be assigned somehow if it's true
 * list of methods, and the index is the givenscore
 *
 * */

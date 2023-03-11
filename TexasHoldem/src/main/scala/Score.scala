import Classes.{Card, Player, Table}

class Score(player:Player, table:Table) {

  //score class gets a score given a player and the table - lowest score wins
  private val playerCards:Seq[Card] = player.hand
  private val tableCards:Seq[Card] = table.table
  var currentHand:Seq[Card] = playerCards ++ tableCards
  val currentHandSuits = currentHand.map(_.givenSuit)
  val currentHandValues = currentHand.map(_.givenValue)


  //method to check if the value is sequential
  def isSequential(listOfCards:Seq[Card]): Boolean = {
    val listOfCardValues = listOfCards.map(_.givenValue)
    val deckSequence= Seq[String]("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    val equivalentValues = Map[String,Int](
    "1"->1,
    "2"->2,
    "3"->3,
    "4"->4,
    "5"->5,
    "6"->6,
    "7"->7,
    "8"->8,
    "9"->9,
    "10"->10,
    "J"->11,
    "Q"->12,
    "K"->13,
    "A"->14
    )
    val cardValuesToEquivalentValues = for{i<-listOfCardValues} yield(equivalentValues(i))
    //converts seq of values of cards into equivalent values (1-14)
    val orderedValueList = cardValuesToEquivalentValues.sorted
    //orders the list ascending

    val firstValue = orderedValueList.head
    //gets the head value of the sorted list of ints

    if(firstValue >10) {
      false
    } else {
      //get a startingIndex value to see where the straight would start. this maps the value for the start of the straight
      val startingIndex = deckSequence(firstValue - 1)
      //get index of the head value within the orderedCardValues list
      val potentialStraight = for {i <- startingIndex.toInt until (startingIndex.toInt + 5)} yield deckSequence(i-1)
      //this gives a potential straight based on the first value/head value given (runs)

      val orderedListOfCardValues = for {i <- orderedValueList} yield deckSequence(i-1)
      //converts back to an orders Seq of card values to compare to the potentialStraight
      //i-1 because deckSequenceIndex starts at 0.

      val matchedValuesList = for {i <- orderedListOfCardValues.zip(potentialStraight)} yield i._1 == i._2
      //zip potentialStraight values with the listOfOrderedCard values

      matchedValuesList.forall(_ == true)
    }
  }

//takes a list of suits and returns true if all is the same
  def isAllSameSuit(listOfCards: Seq[Card]): Boolean = {
    val head = listOfCards.map(_.givenSuit).head
    listOfCards.map(_.givenSuit).forall(_ == head)

  }
//straightFlush checker
  def isStraightFlush(hand:Seq[Card]):Boolean = {
    //checks the cards for sequential
    isAllSameSuit(hand) && isSequential(hand)
  }

  //royalFlush checker
  def isRoyalFlush(hand:Seq[Card]):Boolean = {
    //firstCard in the sorted list of card values should be 10
    val sortedListOfCardValues = hand.map(_.givenValue).sorted
    if(sortedListOfCardValues.head != "10") {
      false
    } else {
      isStraightFlush(hand)
    }
  }

  //checks if there is a 4 of same values within the hand. If first or second value does not have a count of 4 within
  //the hand then 4 of a kind is impossible
  def isFourOfAKind(hand:Seq[Card]): Boolean = {
    val cardValues = hand.map(_.givenValue)
    cardValues.count(_==cardValues(0)) == 4 || cardValues.count(_==cardValues(1)) == 4
  }



}

  /**
   * Texas Hold 'em - part two
   *
   * This doesn't really require the previous week's work, but if you've got it then you can just slot it in there. This week we're going to try to classify poker hands. Poker has the following types of hands:
   *
   * 1Royal flush: A-K-Q-J-10, all the same suit
   * 2Straight flush: Five cards in a sequence, all in the same suit
   * 3Four of a kind: All four cards of the same rank
   * 4Full house: Three of a kind with a pair
   * 5Flush: Any five cards of the same suit, but not in a sequence
   * 6Straight: Five cards in a sequence, but not of the same suit
   * 7Three of a kind: Three cards of the same rank
   * 8Two pair: Two different pairs
   * 9Pair: Two cards of the same rank
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
   *gets a score of the current hand
   *
   * use of a while loop (while return value != Int){do these methods until true} <- the methods will be the bools for line 8-18
   * goes through the ranked methods and breaks at a point where true and assigns this score to the hand, then compares hands
   * 1) write the methods for true/false for each one and return true
   * 2) score assignment? hand score needs to be assigned somehow if it's true
   * list of methods, and the index is the givenscore
   *
    **/



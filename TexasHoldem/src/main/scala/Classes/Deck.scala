package Classes

import scala.util.Random

  class Deck() {
    //create the deck of 52
    val suit = Seq[String]("clubs", "hearts", "spades", "diamonds")
    val value = Seq[String]("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    var currentDeck = createDeck()

    //deck contains a seq of card objects
    def createDeck(): Seq[Card] = {
      for {i <- suit
           j <- value} yield new Card(j, i)
    }

    //passes a card instnace in and removes it from list (actually creates a new list)
    def removeCard(card: Card): Seq[Card] = {
      currentDeck.filterNot(_ == card)
    }

    def updatedDeck(deck: Seq[Card]): Unit = {
      currentDeck = deck
    }

    def randomCard(): Card = {
      val randomInt = Random.between(0, currentDeck.length)
      currentDeck(randomInt)
    }

    //deals a card and removes it from the deck
    def dealPlayer(player: Player): Unit = {
      val card = randomCard()
      player.getCard(card)
      val newDeck = removeCard(card)
      updatedDeck(newDeck)
    }

    def dealTable(table: Table): Unit = {
      val card = randomCard()
      table.getCard(card)
      val newDeck = removeCard(card)
      updatedDeck(newDeck)
    }
  }



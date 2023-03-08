import Classes.{Deck, Player, Table}


import scala.util.Random

object run extends App {

  /**
   * We're going to code up dealing Texas Hold 'em hands. There's a summary of the game on Wikipedia if you're not familiar.
   *
   * The game uses a standard deck of 52 cards. The game proceeds as follows:
   *
   * The dealer deals two cards to each player (called hole cards), which no-one else sees.
   * The dealer then discards a card from the deck and deals three cards out on to the table which everyone sees -- this is called the flop.
   * The dealer then discards a card from the deck and deals a fourth card to the table -- this is called the turn.
   * The dealer then discards a card from the deck and deals a fifth card to the table -- this is called the river.
   * There's a whole bunch of additional complexity to do with betting and raising, but we'll ignore that for now. We're just going to handle dealing the cards to the players and the table as described above.
   *
   * The discarding of a card between deals doesn't change any odds but is traditional so let's keep that in.
   */


  val b = new Deck()
  println(b.currentDeck.length)
  println(b.randomCard())
  val c = b.randomCard()
  println(c)
  b.updatedDeck(b.removeCard(c))
  println(b.currentDeck)
  val player = new Player
  b.dealPlayer(player)
  println(player.hand)
  println(b.currentDeck)
  b.dealPlayer(player)
  println(player.hand)
  println(b.currentDeck)
  player.clearHand()
  println("hand=", player.hand)

  b.removeCard(b.randomCard())

  val table = new Table()
  b.dealTable(table)
  b.dealTable(table)
  b.dealTable(table)

  println(table.table)
  println(b.currentDeck)

  b.dealTable(table)
  b.dealTable(table)

  println("table=",table.table)
  println("deck=", b.currentDeck)
}

//
//}

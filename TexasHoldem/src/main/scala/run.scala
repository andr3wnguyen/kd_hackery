import Classes.{Deck, Player, Score, Table}

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


  //create deck
  val b = new Deck()
  println("> Dealing cards... ")

  //dealing 2 cards to a player
  val player = new Player
  b.dealPlayer(player)
  b.dealPlayer(player)

  println(s"[] Your cards: ${player.hand.map(_.toString).mkString(", ")} []")

  //deal 2 cards to house
  val house = new Player
  b.dealPlayer(house)
  b.dealPlayer(house)

  //  println(s"House cards: ${house.hand.map(_.toString).mkString(", ")}")

  val table = new Table

  //flop
  b.dealTable(table)
  b.dealTable(table)
  b.dealTable(table)

  //flop
  println(" ")
  println(s"[] Table cards: ${table.table.map(_.toString).mkString(", ")} []")

  var in = true
  var folded = false
  while (in && table.table.length < 5) {
    println(" ")
    println("Deal? y/n")
    println(" ")
    val userInput = scala.io.StdIn.readLine().toLowerCase()
    if (userInput == "y") {
      // Continue the program
      println(" ")
      println("Dealing...")
      println(" ")
      b.dealTable(table)
      println(s"Table cards ${table.table}")
    } else if (userInput == "n") {
      // End the loop and exit the program
      println("FOLDED.")
      in = false
      folded = true
    } else {
      // Handle invalid input
      println("Invalid input. Please enter 'y' or 'n'.")
    }
  }

  //tally the scores once the river
  if (folded == false) {
    println("=========================================================")
    println(s"Your cards: ${player.hand.map(_.toString).mkString(", ")}")
    println(s"House cards: ${house.hand.map(_.toString).mkString(", ")}")
    println("=========================================================")
    println(s"Table: ${table.table.map(_.toString).mkString(", ")}")
    println("=========================================================")
    val playerScore = new Score(player, table)
    val houseScore = new Score(house, table)

    //compare hands
    if (playerScore.getCurrentHandsScore < houseScore.getCurrentHandsScore) {
      println(s"You win with ${playerScore.winCondition}.")
    }
    else if (playerScore.getCurrentHandsScore == houseScore.getCurrentHandsScore) {
      println(s"Split pot, you have ${playerScore.winCondition} and house also has ${houseScore.winCondition}")
    }
    else
      println(s"House wins with ${houseScore.winCondition}")

  }

}

//currently flawed logic for if both get 2 pairs.

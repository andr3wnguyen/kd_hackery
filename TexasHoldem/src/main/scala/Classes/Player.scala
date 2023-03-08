package Classes

class Player() {
  var hand = Seq[Card]().empty

  def getCard(card: Card): Unit = {
    hand = hand :+ card
  }

  def clearHand(): Unit = {
    hand = Seq.empty[Card]
  }

}
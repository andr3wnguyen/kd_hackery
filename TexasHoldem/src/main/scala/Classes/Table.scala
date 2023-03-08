package Classes

class Table() {
  var table = Seq[Card]().empty

  def getCard(card: Card): Unit = {
    table = table :+ card
  }

  def clearHand(): Unit = {
    table = Seq.empty[Card]
  }
}
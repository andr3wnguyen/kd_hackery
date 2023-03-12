import Classes.{Card, Player, Table}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ScoreSpec extends AnyFunSpec with Matchers {
      val card1 = Card("spades","J")
      val card2 = Card("spades","10")
      val card3 = Card("spades","Q")
      val card4 = Card("spades","K")
      val card5 = Card("spades","A")
      val card6 = Card("hearts","3")
      val card7 = Card("spades", "8")
      val card8 = Card("spades", "9")
      val card9 = Card("diamonds", "3")
      val card10 = Card("spades", "3")
      val card11 = Card("clubs", "3")
      val card12 = Card("hearts", "K")

      val player = new Player()
      player.getCard(card1)
      player.getCard(card2)
      player.getCard(card3)
      val table = new Table()
      table.getCard(card4)
      table.getCard(card5)
      val player2 = new Player()
      player2.getCard(card1)
      player2.getCard(card2)
      player2.getCard(card6)
      val table2 = new Table()
      table2.getCard(card7)
      table2.getCard(card8)
      val player3 = new Player()
      player3.getCard(card9)
      player3.getCard(card10)
      player3.getCard(card11)
      val table3 = new Table()
      table3.getCard(card7)
      table3.getCard(card6)
      val player4 = new Player()
      player4.getCard(card1)
      player4.getCard(card11)
      player4.getCard(card12)
      val table4 = new Table()
      table4.getCard(card4)
      table4.getCard(card12)

  describe("Score") {
    it("initiates a Score object") {
      //given
      val playerObj = player
      val tableObj  = table
      val expectedHandLength = 5
      val expectedCurrentHand = List(Card("spades", "J"), Card("spades", "10"), Card("spades", "Q"), Card("spades", "K"), Card("spades", "A"))

      //when
      val score = new Score(playerObj, tableObj)
      val actualCurrentHand = score.currentHand
      val actualCurrentHandLength = score.currentHand.length

      //then
      actualCurrentHand shouldBe expectedCurrentHand
      actualCurrentHandLength shouldBe expectedHandLength
    }

    it("should correctly return true if all suits match - checks if flush (also checks isAllSameSuitMethod") {
      //given
      val playerObj = player
      val tableObj = table
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultSameSuit = score.isFlush(scoreCurrentHand)
      val actualResultNotSameSuit = score2.isFlush(score2CurrentHand)

      //then
      actualResultSameSuit shouldBe true
      actualResultNotSameSuit shouldBe false

    }

    it("should correctly return right boolean if a hand is a straight") {
      //given
      val playerObj = player
      val tableObj = table
      val player2Obj = player2
      val tableObj2 = table2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val score3 = new Score(playerObj, tableObj2)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand
      val score3CurrentHand = score3.currentHand

      println(scoreCurrentHand)
      println(score2CurrentHand)
      println(score3CurrentHand)

      val actualResultSequential = score.isSequential(scoreCurrentHand)
      val actualResultNotSequential = score2.isSequential(score2CurrentHand)
      val actualResultSequential2 = score3.isSequential(score3CurrentHand)

      //then
      actualResultSequential shouldBe true
      actualResultSequential2 shouldBe true
      actualResultNotSequential shouldBe false


    }

    it("should correctly return right boolean if a straight flush is passed in  "){
      val playerObj = player
      val tableObj = table2
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      println(scoreCurrentHand)
      println(score2CurrentHand)


      val actualResultStraightFlush = score.isStraightFlush(scoreCurrentHand)
      val actualResultNotStraightFlush = score2.isStraightFlush(score2CurrentHand)

      //then
      actualResultStraightFlush shouldBe true
      actualResultNotStraightFlush shouldBe false
    }

    it("should correctly return right boolean if a royal flush is passed in  ") {
      val playerObj = player
      val tableObj = table
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultStraightFlush = score.isRoyalFlush(scoreCurrentHand)
      val actualResultNotStraightFlush = score2.isRoyalFlush(score2CurrentHand)

      //then
      actualResultStraightFlush shouldBe true
      actualResultNotStraightFlush shouldBe false

    }

    it("should return the right boolean for a 4 of a kind") {
      val playerObj = player3
      val tableObj = table3
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultFourOfAKind = score.isFourOfAKind(scoreCurrentHand)
      val actualResultNotFourOfAKind = score2.isFourOfAKind(score2CurrentHand)

      //then
      actualResultFourOfAKind shouldBe true
      actualResultNotFourOfAKind shouldBe false
    }

    it("should test 3 of a kind method to return true if a card turns up 3 times") {
      //given
      val playerObj = player3
      val tableObj = table
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultThreeOfAKind = score.isThreeOfaKind(scoreCurrentHand)
      val actualResultNotThreeOfAKind = score2.isThreeOfaKind(score2CurrentHand)

      //then
      actualResultThreeOfAKind shouldBe true
      actualResultNotThreeOfAKind shouldBe false

    }

    it ("should test isAPair method to return true if a card turns up 2 times") {
      //given
      val playerObj = player4
      val tableObj = table
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultIsAPair = score.isAPair(scoreCurrentHand)
      val actualResultNotIsAPair = score2.isAPair(score2CurrentHand)

      //then
      actualResultIsAPair shouldBe true
      actualResultNotIsAPair shouldBe false

    }
    it("should test isAFullHouse method to return true if there is a three or a kind and a pair") {
      //given
      val playerObj = player3
      val tableObj = table4
      val player2Obj = player2

      //when
      val score = new Score(playerObj, tableObj)
      val score2 = new Score(player2Obj, tableObj)
      val scoreCurrentHand = score.currentHand
      val score2CurrentHand = score2.currentHand

      val actualResultIsFullHouse = score.isFullHouse(scoreCurrentHand)
      val actualResultNotFullHouse = score2.isFullHouse(score2CurrentHand)

      //then
      actualResultIsFullHouse shouldBe true
      actualResultNotFullHouse shouldBe false
    }




  }
}
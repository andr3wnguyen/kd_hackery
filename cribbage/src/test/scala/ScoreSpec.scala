import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ScoreSpec extends AnyFunSpec with Matchers {
  val card1 = Card("spades", "J")
  val card2 = Card("spades", "5")
  val card3 = Card("spades", "1")
  val card4 = Card("spades", "2")
  val card5 = Card("spades", "9")
  val card6 = Card("hearts", "3")
  val card7 = Card("spades", "8")
  val card8 = Card("spades", "9")
  val card9 = Card("diamonds", "3")
  val card10 = Card("spades", "3")
  val card11 = Card("clubs", "3")
  val card12 = Card("hearts", "4")

  val hand = Seq(card1, card2, card3, card4)

  describe("Score") {
    it("initiates a Score object") {
      val score = new Score(hand,card12)
      val actual = score.getScore
      actual shouldBe 0
    }

//    it("adds 2 onto the score when a fifteen is reached") {
//      val score = new Score()
//      score.scoreFifteen(hand)
//      val actual = score.getScore
//      actual shouldBe 15
//    }

    it("scores a  run correctly") {
      val score = new Score(hand, card5)
      score.scoreRuns()
      val actual = score.getScore
      actual shouldBe 0
    }

    it("scores a 5 run correctly") {
      val fiveRunHand = Seq(card2,card3,card4,card6)
      val score = new Score(fiveRunHand,card12)
      score.scoreRuns()
      val actual = score.getScore
      actual shouldBe 5
    }

    it("scores a 4 run correctly") {
      val fiveRunHand = Seq(card1, card3, card4, card6)
      val score = new Score(fiveRunHand, card12)
      score.scoreRuns()
      val actual = score.getScore
      actual shouldBe 4
    }


    it("scores a 3 run correctly") {
      val fiveRunHand = Seq(card1, card3, card4, card6)
      val score = new Score(fiveRunHand, card11)
      score.scoreRuns()
      val actual = score.getScore
      actual shouldBe 3
    }

    it("scores less than a 3 run correctly") {
      val fiveRunHand = Seq(card2, card3, card11, card6)
      val score = new Score(fiveRunHand, card12)
      score.scoreRuns()
      val actual = score.getScore
      actual shouldBe 0
    }

    it("scores a pair correctly") {
      val pair = Seq(card1,card2,card10,card11)
      val score = new Score(pair,card12)
      score.scorePairs()
      val actual = score.getScore
      actual shouldBe 2
    }

    it("scores two pairs correctly") {
      val twoPair = Seq(card1, card9, card10, card11)
      val score = new Score(twoPair,card12)
      score.scorePairs()
      val actual = score.getScore
      actual shouldBe 6
    }

    it("scores three pairs correctly") {
      val threePair = Seq(card6, card9, card10, card11)
      val score = new Score(threePair,card12)
      score.scorePairs()
      val actual = score.getScore
      actual shouldBe 12
    }

    it("scores a flush correctly") {
      val noFlush = Seq(card6, card9, card10, card11)
      val score = new Score(noFlush,card12)
      score.scoreFlush()
      val actual = score.getScore
      actual shouldBe 0
    }


    it("scores nobs correctly") {
      val nob = Seq(card1, card2, card3, card4)
      val score = new Score(nob, card7)
      score.scoreNobs()
      val actual = score.getScore
      actual shouldBe 1
    }
    }

}

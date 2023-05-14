import org.scalatest.funspec.AnyFunSpec

class CheckerSpec extends AnyFunSpec {

  //mock a word
  val word = Word(1)
  val checker = new Checker(word)

  describe("Checker class") {
    it("initiates correctly") {
      assert(checker.wordToCompare == "WORDS")
    }

    it("should generate a coloured array when given a guess") {
      val guess = "words"
      val expectedArray = Array(
        s"${Console.GREEN_B}W${Console.RESET}",
        s"${Console.GREEN_B}O${Console.RESET}",
        s"${Console.GREEN_B}R${Console.RESET}",
        s"${Console.GREEN_B}D${Console.RESET}",
        s"${Console.GREEN_B}S${Console.RESET}"
      )
      assert(checker.generateColouredArray(guess) === expectedArray)
    }

    it("should not update the status when given an incorrect word") {
      val guess = "javas"
      checker.checkStatus(guess)
      assert(checker.status === true)
    }
    it("should update the status to false when given the correct word") {
      val guess = "words"
      checker.checkStatus(guess)
      assert(checker.status === false)
    }

  }




}
case class Word(i: Int) {
  def getWord:String = "words"

}


























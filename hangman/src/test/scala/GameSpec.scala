import Classes.Game
import org.mockito.Mockito.{doAnswer, when}
import org.mockito.invocation.InvocationOnMock
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

class GameSpec extends AnyFunSpec with Matchers with MockitoSugar{
      val mockedWord = mock[Classes.Word]
      when(mockedWord.answer).thenReturn("a")
      when(mockedWord.listOfWords).thenReturn(Seq("answer1","answer2"))
      val game = new Game(mockedWord)

//needs mocking :(
  describe("game class tests") {
    it("can initiate a game given a word, with 7 attempts, 0 guesses, and current board is length of answer") {
      //mock word object - don't think this is technically the way to do it
      assert(game.attemptsLeft == 6)
      assert(game.guesses.length == 0)
      assert(game.gameStatus == true)
      assert(game.currentBoard == ("_" * mockedWord.answer.length))
    }
    it("correct guess") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        game.guess(Some('a'))
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("a was found in the word!"))
    }
    it("incorrect guess") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        game.guess(Some('h'))
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("h was not in the word"))
    }
  }

}

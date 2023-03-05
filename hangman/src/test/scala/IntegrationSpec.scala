import Classes.{Game, Hungman, Word}
import org.scalatest.funspec.AnyFunSpec

class IntegrationSpec extends AnyFunSpec{
describe("app"){
  it("creates an instance of a game with a word dependancy injected ") {
    val word = new Word("_____")
    assert(!word.answer.isEmpty)
    val game = new Game(word)
    assert(game.gameStatus == true)
  }
  it("wins a game") {
    val word = new Word("____")
    val game = new Game(word)
    word.answer = "abba"
    game.guess(Some('a'))
    game.guess(Some('b'))
    val output = new java.io.ByteArrayOutputStream()
    Console.withOut(output) {
      game.gameStatus
    }
    val terminalOutput = output.toString()
    assert(terminalOutput.contains("You win."))
  }

  it("loses a game") {
    val word = new Word("_____")
    word.answer = "answer"
    val game = new Game(word)
    val hangman = new Hungman
    val output = new java.io.ByteArrayOutputStream()
    game.guess(Some('z'))
    game.guess(Some('x'))
    game.guess(Some('f'))
    game.guess(Some('m'))
    game.guess(Some('q'))
    game.guess(Some('j'))
    Console.withOut(output) {
      hangman.draw(game.attemptsLeft)
    }
    val terminalOutput = output.toString()
    assert(terminalOutput.contains("\u2796" * 3))
    assert(terminalOutput.contains("    \u26D3"))
    assert(terminalOutput.contains("    \uD83D\uDE14"))
    assert(terminalOutput.contains("  \ud83d\udcaa" + "\uD83D\uDFE8" + "\uD83E\uDD33"))
    assert(terminalOutput.contains("    \uD83D\uDFE8 	 "))
    assert(terminalOutput.contains("   \uD83E\uDDB5" + "\uD83E\uDDB5" + "\n You lose :(."))
    assert(terminalOutput.contains("You lose :(."))
  }
  it(" returns hangman segement given x amount of incorrect guesses") {
    val word = new Word("_____")
    word.answer = "answer"
    val game = new Game(word)
    val hangman = new Hungman
    val output = new java.io.ByteArrayOutputStream()
    game.guess(Some('z'))
    game.guess(Some('x'))
    game.guess(Some('f'))
    Console.withOut(output) {
      hangman.draw(game.attemptsLeft)
    }
    val terminalOutput = output.toString()
    assert(terminalOutput.contains("\u2796" * 3))
    assert(terminalOutput.contains("    \u26D3"))
    assert(terminalOutput.contains("    \uD83D\uDE14"))
    assert(game.attemptsLeft == 3)
  }
  }
  it("adds the guess to a list of guesses regardless of right or wrong") {
  val word = new Word("_____")
  word.answer = "answer"
  val game = new Game(word)
  val hangman = new Hungman
  val output = new java.io.ByteArrayOutputStream()
  Console.withOut(output) {
    game.guess(Some('a'))
    game.guess(Some('q'))
  }
  val terminalOutput = output.toString()
  assert(terminalOutput.contains("q"))
  assert(terminalOutput.contains("a"))
  }
  it("returns if the guess was in the word or not") {
  val word = new Word("_____")
  word.answer = "answer"
  val game = new Game(word)
  val hangman = new Hungman
  val output = new java.io.ByteArrayOutputStream()
  Console.withOut(output) {
    game.guess(Some('a'))
    game.guess(Some('q'))
  }
  val terminalOutput = output.toString()
  assert(terminalOutput.contains("q was not in the word"))
  assert(terminalOutput.contains("a was found in the word"))
}
}

import Classes.Hungman
import org.scalatest.funspec.AnyFunSpec



class HungmanSpec extends AnyFunSpec{

val hangman = new Hungman()

  describe("Draw method tested") {
    it("should print the hanged man with 0 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(0)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
      assert(terminalOutput.contains("    \u26D3"))
      assert(terminalOutput.contains("    \uD83D\uDE14"))
      assert(terminalOutput.contains("  \ud83d\udcaa" + "\uD83D\uDFE8" + "\uD83E\uDD33"))
      assert(terminalOutput.contains("    \uD83D\uDFE8 	 "))
      assert(terminalOutput.contains("   \uD83E\uDDB5" + "\uD83E\uDDB5" + "\n You lose :(."))
    }
    it("should print the hanged man but one emoji with 1 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(1)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
      assert(terminalOutput.contains("    \u26D3"))
      assert(terminalOutput.contains("    \uD83D\uDE14"))
      assert(terminalOutput.contains("  \ud83d\udcaa" + "\uD83D\uDFE8" + "\uD83E\uDD33"))
      assert(terminalOutput.contains("    \uD83D\uDFE8 	 "))
    }
    it("should print the hanged man but two emojis with 2 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(2)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
      assert(terminalOutput.contains("    \u26D3"))
      assert(terminalOutput.contains("    \uD83D\uDE14"))
      assert(terminalOutput.contains("  \ud83d\udcaa" + "\uD83D\uDFE8" + "\uD83E\uDD33"))
    }
    it("should print the hanged man but three emojis with 3 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(3)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
      assert(terminalOutput.contains("    \u26D3"))
      assert(terminalOutput.contains("    \uD83D\uDE14"))
    }
    it("should print the hanged man but four emojis with 4 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(4)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
      assert(terminalOutput.contains("    \u26D3"))
    }
    it("should print the hanged man but five emojis with 5 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(5)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("\u2796" * 3))
    }
    it("should print nothing when 6 passed to it") {
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        hangman.draw(6)
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains(""))
    }
  }


}

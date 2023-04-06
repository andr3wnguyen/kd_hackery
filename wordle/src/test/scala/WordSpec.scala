import org.scalatest.funspec.AnyFunSpec

class WordSpec extends AnyFunSpec {

      val word = new Word(1)
  describe("Word") {


    it("initiates correctly") {
      assert(word.getWord.length > 0)
    }

    it("returns the word") {
      assert(!word.getWord.isEmpty)
    }
  }

}


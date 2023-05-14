import org.scalatest.funspec.AnyFunSpec

class FirstWordSpec extends AnyFunSpec{

  describe("WordOne") {
    it("gets the right range of indexes for a given word"){
      val firstWord = new FirstWord("foo")
      assert(firstWord.wordRange == (0 to 2))
    }
    it("gets the right range of indexes for a longer given word") {
      val firstWord = new FirstWord("foobar")
      assert(firstWord.wordRange == (0 to 5))
    }

    it("takes a word and creates an array of wordlets") {
      val firstWord = new FirstWord("foo")
      assert(firstWord.wordlets == Seq("oo","fo", "fo"))
    }
    it("takes a longer word and creates an array of wordlets successfully") {
      val firstWord = new FirstWord("foobar")
      assert(firstWord.wordlets == Seq("oobar", "fobar", "fobar", "fooar", "foobr","fooba"))
    }

  }
}

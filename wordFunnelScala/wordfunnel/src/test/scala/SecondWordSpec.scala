import org.scalatest.funspec.AnyFunSpec

class SecondWordSpec extends AnyFunSpec {


  describe("WordTwo") {
    it("stores the second word into a field") {
      val secondWord = new SecondWord("foo")
      assert(secondWord.wordTwo == "foo")
    }
  }
}

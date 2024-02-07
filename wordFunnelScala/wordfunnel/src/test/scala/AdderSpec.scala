import org.scalatest.funspec.AnyFunSpec

class AdderSpec extends AnyFunSpec {

  describe("adder") {
    it("adds the absolute of two values") {
      val adder = new Adder
      val answer = adder.addAbsoluteValues(1, 2)
      assert(answer == 3)
    }
    it("adds the absolute of two values but one is a big negative") {
      val adder = new Adder
      val answer = adder.addAbsoluteValues(-4, 2)
      assert(answer == 2)
    }

  }
}
import org.scalatest.funspec.AnyFunSpec
import run.TimeConverter


class TalkingClockSpec extends AnyFunSpec {
val clock = new TimeConverter()

  describe("Clock class") {
    it("converts times correctly"){
      assert(clock.convert("21:21")=="oh nine twenty one PM")
      assert(clock.convert("00:00")=="twelve oh oh AM")
      assert(clock.convert("08:10")=="oh eight ten AM")
    }
  }

}

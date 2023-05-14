import org.scalatest.funspec.AnyFunSpec

class FunnelSpec extends AnyFunSpec{
  val funnel = new Funnel
  val firstWord = new FirstWord("foo")
  val secondWord = new SecondWord("oo")
  val firstWordFalse = new FirstWord("foobar")
  val secondWordFalse = new SecondWord("notfooorbar")

  describe("Funnel"){
    it("funnels 2 words correctly") {
      assert(funnel(firstWord, secondWord) == true)
      assert(funnel(firstWordFalse, secondWordFalse) == false)
    }
  }

}
//case class First(word:String) {
//  val wordlets = Seq("of","fo","fo")
//
//}
//case class Second(word:String) {
//  val wordTwo = "fo"



//needs mock wordOne and wordTwo
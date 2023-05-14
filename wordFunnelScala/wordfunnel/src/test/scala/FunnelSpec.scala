import org.scalatest.funspec.AnyFunSpec

class FunnelSpec extends AnyFunSpec{
  val funnel = new Funnel
  val firstWord = new FirstWord("foo")
  val secondWord = new SecondWord("oo")
  val firstWordFalse = new FirstWord("boats")
  val secondWordFalse = new SecondWord("notfooorbar")

  describe("Funnel"){
    it("funnels 2 words correctly") {
      assert(funnel(firstWord, secondWord) == true)
      assert(funnel(firstWordFalse, secondWordFalse) == false)
    }
  }

  it("loads words from the text file correctly"){
    assert(funnel.getAllWords.length.equals(172823))
  }


  it("gets all bonus words from a list of all possible words (all_words.txt)"){
    //write a test for this
    assert(funnel.getAllPossibleWords(firstWordFalse).equals(Seq("oats", "bats", "bots", "boas", "boat")))
  }
}
//case class First(word:String) {
//  val wordlets = Seq("of","fo","fo")
//
//}
//case class Second(word:String) {
//  val wordTwo = "fo"



//needs mock wordOne and wordTwo
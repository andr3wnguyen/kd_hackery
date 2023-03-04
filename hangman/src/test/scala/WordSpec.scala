import Classes.Word
import org.scalatest.funspec.AnyFunSpec

class WordSpec extends AnyFunSpec{

  describe("Word class for word generation") {
    it("reads from the txt file containing words and there are more than 3 values for each length of word"){
      val word3 = new Word("___")
      val word4 = new Word("____")
      val word5 = new Word("_____")
      val word6 = new Word("______")
      val word7 = new Word("_______")
      val word8 = new Word("________")
      assert(word3.listOfWords.length > 3)
      assert(word4.listOfWords.length > 3)
      assert(word5.listOfWords.length > 3)
      assert(word6.listOfWords.length > 3)
      assert(word7.listOfWords.length > 3)
      assert(word8.listOfWords.length > 3)
    }
    it("generates a word given a currentBoard value (current board is '_' multiplied by an int but can be injected as a string e.g. '___' instead of _*3"){
      val word3 = new Word("___")
      val word4 = new Word("____")
      val word5 = new Word("_____")
      val word6 = new Word("______")
      val word7 = new Word("_______")
      val word8 = new Word("________")
      assert(word3.answer.length == 3)
      assert(word4.answer.length == 4)
      assert(word5.answer.length == 5)
      assert(word6.answer.length == 6)
      assert(word7.answer.length == 7)
      assert(word8.answer.length == 8)
      //does this have to be mocked?
    }
    it("word changes to a different one when passed an allowed state (blank values will suffice)") {
      val word = new Word("_____")
      val word1 = word.answer
      word.changeWord("_____")
      val word2 = word.answer
      println(word1, word2)
      assert(word1 != word2)
    }
    it("changes word for a similar word that has same revealed letters") {
      val word = new Word("l____")
      val word1 = word.answer
      word.changeWord("l____")
      val word2 = word.answer
      //println(word1, word2)
      assert(word1 != word2)
      assert(word1(0) == 'l')
      assert(word2(0) == 'l')
    }
    it("keeps the same word if there is only 1 possible outcome") {
      val word = new Word("l_nky")
      val word1 = word.answer
      word.changeWord("l_nky")
      val word2 = word.answer
      println(word1, word2)
      assert(word1 == word2)
    }
    it("changes word for a similar word that has same revealed letters, second letter of 'o'") {
      val word = new Word("_o__")
      val word1 = word.answer
      word.changeWord("_o__")
      val word2 = word.answer
      //println(word1, word2)
      assert(word1 != word2)
      assert(word1(1) == 'o')
      assert(word2(1) == 'o')
    }
  }



}

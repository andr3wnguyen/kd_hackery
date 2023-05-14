import scala.io.Source

class Funnel {


  def apply(wordOne:FirstWord, wordTwo:SecondWord): Boolean = {
    wordOne.wordlets.contains(wordTwo.wordTwo)
  }

  //reads from a file (could use an api call? Idk lol)
  def getAllWords: Seq[String] = {
    Source.fromResource("all_words.txt").getLines.toList
  }

  //checks if taking off a letter creates a word contained in getAllWords
  def getAllPossibleWords(wordOne:FirstWord): Seq[String] = {
    val a = getAllWords
    wordOne.wordlets.filter(x => a.contains(x))
  }

  def bonus(wordOne:FirstWord): Unit = {
    val allWords = getAllPossibleWords(wordOne)
    allWords.foreach(println)
  }
}

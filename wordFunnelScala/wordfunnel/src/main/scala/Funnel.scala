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
  def printAllPossibleWords(wordOne:FirstWord): Unit = {
    val a = getAllWords
    for(i<-wordOne.wordlets){if(a.contains(i)) println(i)}
  }

}

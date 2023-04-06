import scala.io.Source
import scala.util.Random

class Word(gameMode:Int) {
  val listOfWords = cacheWords(gameMode)
  val newWord = setWord

  private def cacheWords(gameMode:Int): Seq[String] =
  {
    if(gameMode == 1){
      Source.fromResource("dictionary.txt").getLines.filter(_.length==5).toList
    }
    else if(gameMode == 2) {
      Source.fromResource("pokemon.txt").getLines.toList}
    else {
      Source.fromResource("dictionary.txt").getLines.toList}
  }

  private def setWord: String =
    {
      val randomise = Random.between(0,listOfWords.length)
      listOfWords(randomise)
    }

  def getWord: String =
    newWord

}

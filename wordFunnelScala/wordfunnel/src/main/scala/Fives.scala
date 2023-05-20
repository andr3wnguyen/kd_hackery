import scala.io.Source

class Fives{

  val wordList = getAllWords


  def getAllWords: Seq[String] = {
//    Source.fromResource("testwords.txt").getLines.toList
    Source.fromResource("all_words.txt").getLines.toList
  }

  def possibleWords(length:Int): Seq[String] = {
    wordList.filter(x=>x.length == length)
  }

  def wordlets(word:String): Set[String] = {
    (0 to word.length-1).map(x => word.substring(0,x)+ word.substring(x+1)).toSet
  }


  def funnel(wordlets: Set[String]): Seq[String] = {
//    println(wordlets.filter(word => possibleWords(word.length).contains(word)))
    wordlets.filter(word => possibleWords(word.length).contains(word)).toSeq
  }

  def check(word: String): Boolean = {
//      println(funnel(wordlets(word)).length == 5)
      funnel(wordlets(word)).length == 5
  }

  def bonusFunnel: Seq[String] = {
    wordList.filter(x => check(x))
  }

}
//creates wordlets -> whilst length of wordslets >=5, check each one is in the allwords


//filter all possible words so that only checking against a.length -1
//all values are length >5

//for each word -> shove it into the bonus funnel
//
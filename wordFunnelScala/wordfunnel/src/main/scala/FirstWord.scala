class FirstWord(word:String) {
//takes a word

  val wordlets = createWordlets(word)

  //creates a list of wordlets

  //calculates a range with the indexes of each letter
  def wordRange: Range = {
    0 to word.length-1
  }

  def createWordlets(word:String): Seq[String] = {
    wordRange.map(x => word.substring(0,x) + word.substring(x+1))
  }

}

object run extends App {
  //takes a val

  class TimeConverter(){
    var meridium = "AM"

    val listOfMinutes = scala.io.Source.fromResource("minutes.txt").getLines().toSeq

    val hoursMap:Map[Int,String] = Map(
      1->"oh one",
      2->"oh two",
      3->"oh three",
      4->"oh four",
      5->"oh five",
      6 ->"oh six",
      7->"oh seven",
      8 -> "oh eight",
      9 -> "oh nine",
      10 -> "ten",
      11 -> "eleven",
      12 -> "twelve"
    )

    def splitString(timeString:String):Seq[Int] = {
      timeString.split(":").map(_.toInt)
    }

    def hoursToWords(hours:Int):String= {
      if(hours==0) {
        meridium = "AM"
        hoursMap(12)
      }
      else if(hours>12){
        meridium = "PM"
        hoursMap(hours-12)
      }
      else {
        meridium = "AM"
        hoursMap(hours)
      }
    }

    def minutesToWords(minutes:Int): String = {
      listOfMinutes(minutes)
    }
    def convert(time:String):String = {
      val hour = splitString(time)(0)
      val minutes = splitString(time)(1)
      s"${hoursToWords(hour)} ${minutesToWords(minutes)} $meridium"
    }
    def apply(inputText: String): Unit = {
      val convertedString = convert(inputText)
      Runtime.getRuntime.exec(s"say $convertedString")
    }

}




}

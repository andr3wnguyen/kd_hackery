class Draw {


  def drawFloor(floor: String): Unit = {
        println("+---+")
        println(s"| ${floor} |")
        println("+---+")
      }

  //map the elevaotr id with a list of floors/current position
  //takes number of floors, elevatorId and current position and creates a seq
  def describeFloors(numberOfFloors: Int, elevatorsAndCurrentPositions: Seq[Map[Int,Int]]): Seq[Map[Int,Seq[String]]] = {
    //create a seq which maps every single floor and elevator situation
    val seqOfFloors = numberOfFloors to 0 by -1
    elevatorsAndCurrentPositions.map(x => Map(x.keys.head->seqOfFloors.map(y => if(y!=x.values.head) y.toString else (s"${Console.YELLOW_B}$y${Console.RESET}"))) )
    }

  def drawBuilding(floorDescription:Seq[Map[Int,Seq[String]]]): Unit = {
    //using the floorDesc print the lift id, followed by the the floors
    //for each value in the seq, print the head, then draw the tail using the seq
    // Seq(Map(1->Seq('a','b','c')),Map(2->Seq('b','c','d')))
    for (x <- floorDescription) {
      val keys = x.keys.head
      val values = x.values.head
      println(s"Elevator Number ${keys}")
      for(x<-values) {drawFloor(x)
      }
    }
  }

  }


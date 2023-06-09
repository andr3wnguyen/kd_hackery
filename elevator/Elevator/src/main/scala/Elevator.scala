class Elevator {
//how does an elevator know what floor it is on
//  var currentFloor = 0
//  var goingTo = 0


  def doorOpen(): Unit = {
    println(s"The door is opening.")
  }

//  def currentFloor(floor:Floor): Unit = {
//    println(s"This is floor ${floor.floorNumber}")
//  }
  def doorClose(): Unit = {
    println("The door is closing.")
  }
  def goingTo(floor:Floor): Unit = {
    println(s"Going to floor ${floor.floorNumber}.")
//    goingTo = floor.floorNumber
  }


  //for door open and close timer
  def doorTimer(seconds:Int): Unit = {
    Thread.sleep(seconds*1000)
  }


}

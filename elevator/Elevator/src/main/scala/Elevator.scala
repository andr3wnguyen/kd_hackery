class Elevator {
//how does an elevator know what floor it is on
//  var currentFloor = 0
//  var goingTo = 0


  def doorOpen(floor:Floor): Unit = {
    println(s"The door is opening. This is floor ${floor.floorNumber}")
//    currentFloor = floor.floorNumber
  }

  def doorClose(floor:Floor): Unit = {
    Thread.sleep(3000)
    println(s"The door is now closing. Going to floor ${floor.floorNumber}")
//    goingTo = floor.floorNumber
  }


}

class Elevator(id:Int) {
//how does an elevator know what floor it is on
//  var currentFloor = 0
//  var goingTo = 0
  var active = false
//  var working = true
  val elevatorId = id
  var currentFloor = 0


  def doorOpen(): Unit = {
    println(s"The door is opening.")
  }

  def setCurrentFloor(floor:Floor): Unit = {
    currentFloor = floor.floorNumber
  }

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

  def switchState(): Unit = {
    active = !active
  }

  def toggleElevatorStatus(): Unit = {
    active = !active
  }
}

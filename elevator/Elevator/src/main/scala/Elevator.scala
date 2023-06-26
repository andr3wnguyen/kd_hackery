class Elevator(id:Int) {

  var active = false
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

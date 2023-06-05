class Floor(number:Int) {
  val floorNumber = number
  var elevatorStatus = if(floorNumber == 0) true else false


  def toggleElevatorStatus(): Unit = {
    elevatorStatus = !elevatorStatus
  }
//if a floor is passed an elevator, then floor has elevatorStatus true else false

}

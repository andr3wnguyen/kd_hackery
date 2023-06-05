import scala.collection.mutable

class ElevatorController(building:Building){
  var queue = mutable.Queue[Floor]()
  var elevatorCurrent = getCurrentElevatorFloor()

  //tracks the elevators and floors
  //has a queue that holds calls
  //executes in order of calls

//  def elevatorQueue(): mutable.Queue[Int] = {
//    mutable.Queue()
//  }

  //queue is altered on call
  def call(floor:Floor): Unit = {
    queue.enqueue(floor)
  }

//  def getElevatorFloor(): Int = {
//  //find the floor where the elevatorStatus = true
//    this.building.floors.filter(floor => floor.elevatorStatus == true ).map(elevatorFloor => elevatorFloor.floorNumber).mkString("").toInt
//  }

  def getCurrentElevatorFloor(): Floor = {
    this.building.floors.find(_.elevatorStatus == true).get
  }

//  def getElevatorFloorObject(): Floor = {
//
//  }
//  def moveElevator(floorToGoTo:Floor): Unit = {
//  //finds the current elevator floor object and updates the elevatorStatus (toggle)
//
//    //moves to the next floor and updates the elevatorStatus for this floor
//    this.building.elevator.doorOpen()
//
//
//
//  }


}

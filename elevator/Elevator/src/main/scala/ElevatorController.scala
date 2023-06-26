
//store the state of the current elevator
class ElevatorController(building:Building) {
  var queue = Seq[Floor]().empty.distinct
  var elevatorsAndCurrentPositions = listElevatorsAndCurrentPositions()

  def convertIntToFloor(floorNo: Int): Floor = {
    this.building.floors.find(_.floorNumber == floorNo).get
  }

  def call(floorNumber:Int): Unit = {
    //adds a call to the queue
    val calledFloor = convertIntToFloor(floorNumber)
    if (!queue.contains(calledFloor)) queue = queue :+ calledFloor
    else println(s"Floor ${calledFloor.floorNumber} is already queued.")
  }


  def moveElevator(elevatorId: Int, floorToGoTo: Floor): Unit = {
    //finds the current elevator floor object and updates the elevatorStatus (toggle)
    building.elevators(elevatorId).toggleElevatorStatus()
    //moves to the next floor and updates the elevatorStatus for this floor
    building.elevators(elevatorId).setCurrentFloor(floorToGoTo)
  }

  def clearElevatorQueue():Unit = {
    //clears the elevator queue (unit test)
    queue = Seq[Floor]().empty.distinct

  }

  def getCurrentElevatorFloor(elevatorId:Int): Int = {
    val currentFloorNumber = building.elevators(elevatorId).currentFloor
//    building.floors.find(_.floorNumber==currentFloorNumber).get
    currentFloorNumber
  }
  //gets the closest elevator to the called floor to go

  def getClosestElevator(): Int = {
    val firstQueuedFloor = queue.head.floorNumber
    val listOfElevatorIds = building.elevators.map(x => x.elevatorId) //gets all the elevator ids
    val mapOfElevatorPositionsDifferenceWithIds = listOfElevatorIds.map(x => (x, (getCurrentElevatorFloor(x)-firstQueuedFloor).abs))//gets an id -> position
    mapOfElevatorPositionsDifferenceWithIds.minBy(x => x._2)._1

  }

  //gets a list of all elevators and where they are
  def listElevatorsAndCurrentPositions(): Seq[Map[Int,Int]] = {
    val listOfElevators = building.elevators
    listOfElevators.map(x => Map(x.elevatorId->getCurrentElevatorFloor(x.elevatorId)))
  }

  def updateListElevatorsAndCurrentPositions(listOFElevators: Seq[Map[Int,Int]], updatedElevator:Map[Int,Int]) = {
    //takes new map and updates the elevatorsAndPositions field
    val key = updatedElevator.keys.head
    val value = updatedElevator.values.head
    listOFElevators.find(_.contains(key)).get.updated(key,value)
    }

  def initiateElevator(closestElevatorId:Int): Unit = {

    //changes active state of the elevator to true whilst it is going
    //for the last floor, saves where the elevator is
    building.elevators(closestElevatorId).switchState()
    var listOfElevators = listElevatorsAndCurrentPositions()
    elevatorsAndCurrentPositions = listOfElevators
    while (queue.length > 0) {

      //moves to all the floors in the elevator queue

      for (floor <- queue) {

        // if the floor is the queue is the currentElevator floor, don't announce anything, just remove it from the queue and move onto the next
        //floor as long as the floor is bigger than length 1
        //remove it from the queue and move on
        if(floor.floorNumber != getCurrentElevatorFloor(closestElevatorId)) {
          println(s"Opening elevator ${closestElevatorId}.")
          building.elevators(closestElevatorId).doorOpen()
          println(f"The elevator is on floor: ${getCurrentElevatorFloor(closestElevatorId)}.")
          building.elevators(closestElevatorId).doorTimer(1)
          //close the door
          building.elevators(closestElevatorId).doorClose()

          //don't announce going to that floor because you're already there
          //check if there's any other call -> if yes move on - if not go nout and await a call
          //else remove the floor from the queue and continue
          building.elevators(closestElevatorId).goingTo(floor)
          building.elevators(closestElevatorId).doorTimer(1)
          //move the elevator to that floor
          moveElevator(closestElevatorId,floor)
          updateListElevatorsAndCurrentPositions(listOfElevators,Map(closestElevatorId->floor.floorNumber))

          //last floor
          //if the length of the queue is bigger than 1 delete it. else -> leave the last value in as this is control fo the state
          //if it is at the last call in the queue, switches active state to false and leaves that value in the queue
          if (queue.length == 1) {
            println(f"This is floor ${getCurrentElevatorFloor(closestElevatorId)}.")
            building.elevators(closestElevatorId).switchState()
          }
        }
          else {
            //remove the floor you just went to
            queue = queue.filter(_ != floor)
//            println(s"floors order = ${queue.map(_.floorNumber)}")
          }
        }
      }
    }
  }



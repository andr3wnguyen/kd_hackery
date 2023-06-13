
//store the state of the current elevator
class ElevatorController(building:Building) {
  var queue = Seq[Floor]().empty.distinct
//  var elevatorCurrent = getCurrentElevatorFloor()

  //give each elevator and id
//  var listOfActiveElevators = building.listOfElevators().map(x => Map(x -> x.elevatorId))


  //tracks the elevators and floors
  //has a queue that holds calls
  //executes in order of calls

  //  def elevatorQueue(): mutable.Queue[Int] = {
  //    mutable.Queue()
  //  }

  //queue is altered on call

  def convertIntToFloor(floorNo: Int): Floor = {
    this.building.floors.find(_.floorNumber == floorNo).get
  }

//  def call(floor: Int): Unit = {
//    //within the building a call is passed from the floor which then adds the floor to the queue
//    val calledFloor = convertIntToFloor(floor)
//    if (calledFloor == getCurrentElevatorFloor()) println(s"You are already on floor ${getCurrentElevatorFloor().floorNumber}.")
//    else {
//      if (!queue.contains(calledFloor)) queue = queue :+ calledFloor
//      else println(s"Floor ${calledFloor.floorNumber} is already queued.")
//    }
//  }

  def call(floorNumber:Int): Unit = {
    //adds a call to the queue
    val calledFloor = convertIntToFloor(floorNumber)
    if (!queue.contains(calledFloor)) queue = queue :+ calledFloor
    else println(s"Floor ${calledFloor.floorNumber} is already queued.")
  }


//  def getCurrentElevatorFloor(elevatorId: Int): Int = {
//    //finds elevatorStatus of true and gets the Floor object
//    this.building.elevators(elevatorId).currentFloor
//  }

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
    //check the floor in the queue
    val firstQueuedFloor = queue.head.floorNumber
    //check where the elevators are
    val listOfElevatorIds = building.elevators.map(x => x.elevatorId) //gets all the elevator ids
//    println(listOfElevatorIds)
//    print(" el IDS")
    val mapOfElevatorPositionsDifferenceWithIds = listOfElevatorIds.map(x => (x, (getCurrentElevatorFloor(x)-firstQueuedFloor).abs))//gets an id -> position
    //get the id of the elevator that's closest
//    println(mapOfElevatorPositionsDifferenceWithIds + " Mapped")
//    println(mapOfElevatorPositionsDifferenceWithIds.minBy(x => x._2)._1)
    mapOfElevatorPositionsDifferenceWithIds.minBy(x => x._2)._1

    //get the
  }

  def initiateElevator(closestElevatorId:Int): Unit = {

    //changes active state of the elevator to true whilst it is going
    //for the last floor, saves where the elevator is
    building.elevators(closestElevatorId).switchState()
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







      //        val firstNext = floor //gets value of currentFloor and nextFloor
      //        val currentFloor = firstNext.head
      //        val goingToFloor = firstNext.last
      //        building.elevator.doorOpen(currentFloor) //opens the door and
      //        building.elevator.doorClose(goingToFloor)
      //
      //        moveElevator(firstNext.head)

      //special case -> move to the last elevator floor *
      //where does the lift end?

      //while loop needs to be terminated -> remove the floor from the list that the elevator is leaving?
      //          queue.dequeue(firstNext.head)
      //try iterating over individual

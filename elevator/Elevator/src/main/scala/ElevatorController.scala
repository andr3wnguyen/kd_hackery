import scala.collection.mutable

//store the state of the current elevator
class ElevatorController(building:Building) {
  var queue = Seq[Floor]().empty.distinct
  var elevatorCurrent = getCurrentElevatorFloor()
  var listOfActiveElevators = Seq(this.building.elevator)


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

  def call(floor: Int): Unit = {
    //within the building a call is passed from the floor which then adds the floor to the queue
    val calledFloor = convertIntToFloor(floor)
    if (calledFloor == getCurrentElevatorFloor()) println(s"You are already on floor ${getCurrentElevatorFloor().floorNumber}.")
    else {
      if (!queue.contains(calledFloor)) queue = queue :+ calledFloor
      else println(s"Floor ${calledFloor.floorNumber} is already queued.")
    }
  }
  def getCurrentElevatorFloor(): Floor = {
    //finds elevatorStatus of true and gets the Floor object
    this.building.floors.find(_.elevatorStatus == true).get
  }

  def moveElevator(floorToGoTo: Floor): Unit = {
    //finds the current elevator floor object and updates the elevatorStatus (toggle)
    getCurrentElevatorFloor().toggleElevatorStatus()
    //moves to the next floor and updates the elevatorStatus for this floor
    floorToGoTo.toggleElevatorStatus()
  }

  def initiateElevator(): Unit = {

    //changes active state of the elevator to true whilst it is going
    //for the last floor, saves where the elevator is
    building.elevator.switchState()
    while (queue.length > 0) {

      //moves to all the floors in the elevator queue

      for (floor <- queue) {

        // if the floor is the queue is the currentElevator floor, don't announce anything, just remove it from the queue and move onto the next
        //floor as long as the floor is bigger than length 1
        //remove it from the queue and move on
        if(floor != getCurrentElevatorFloor()) {
          building.elevator.doorOpen()
          println(f"The elevator is on floor: ${getCurrentElevatorFloor().floorNumber}.")
          building.elevator.doorTimer(1)
          //close the door
          building.elevator.doorClose()

          //don't announce going to that floor because you're already there
          //check if there's any other call -> if yes move on - if not go nout and await a call
          //else remove the floor from the queue and continue
          building.elevator.goingTo(floor)
          building.elevator.doorTimer(1)
          //move the elevator to that floor
          moveElevator(floor)


          //last floor
          //if the length of the queue is bigger than 1 delete it. else -> leave the last value in as this is control fo the state
          //if it is at the last call in the queue, switches active state to false and leaves that value in the queue
          if (queue.length == 1) {
            println(f"This is floor ${getCurrentElevatorFloor().floorNumber}.")
            building.elevator.switchState()
          }
        }
          else {
            //remove the floor you just went to
            queue = queue.filter(_ != floor)
            println(s"floors order = ${queue.map(_.floorNumber)}")
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

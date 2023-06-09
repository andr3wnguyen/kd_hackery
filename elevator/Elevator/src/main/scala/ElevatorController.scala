import scala.collection.mutable

//store the state of the current elevator
class ElevatorController(building:Building) {
  var queue = Seq[Floor]().empty.distinct
  var elevatorCurrent = getCurrentElevatorFloor()

  //tracks the elevators and floors
  //has a queue that holds calls
  //executes in order of calls

  //  def elevatorQueue(): mutable.Queue[Int] = {
  //    mutable.Queue()
  //  }

  //queue is altered on call

  def convertIntToFloor(floorNo:Int): Floor = {
    this.building.floors.find(_.floorNumber == floorNo).get
  }

  def call(floor: Floor): Unit = {
    //within the building a call is passed from the floor which then adds the floor to the queue
    if (!queue.contains(floor)) queue = queue :+ floor
    else println(s"Floor ${floor.floorNumber} is already queued.")
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

  // for each value in the queue, moveElevator to each floor
  //how does one queue up the calls?
  def initiateElevator(): Unit = {
    //
    //while there are things in the queue loop through
    //print out where the elevator is currently (current floor)
    //print out where the elevator is going (
    //elevator GETS TO FLOOR THAT IS CALLED
    //OPENS THE DOOR AND TELLS PEOPLE WHERE IT IS
    //CLOSES THE DOOR
    //TELLS PEOPLE WHERE IT IS GOING
    //GOES
    while(queue.length > 1) {
      for (floor <- queue) {
        //announces where elevator is (current)

        building.elevator.doorOpen()
        println(f"This is floor ${getCurrentElevatorFloor().floorNumber}.")
        building.elevator.doorTimer(3)
        //close the door
        building.elevator.doorClose()
        building.elevator.goingTo(floor)
        building.elevator.doorTimer(3)
        //move the elevator to that floor
        moveElevator(floor)

        //last floor
        //if the length of the queue is bigger than 1 delete it. else -> leave the last value in as this is control fo the state
        if (queue.length == 1) println(f"This is floor ${getCurrentElevatorFloor().floorNumber}.")
        else {
          queue = queue.filter(_ != floor)
          println(s"floors order = ${queue.map(_.floorNumber)}")
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
    }
  }
}
import org.scalatest.funspec.AnyFunSpec

import scala.collection.mutable

class ElevatorControllerSpec extends AnyFunSpec {

  val building = new Building(5,2)
  val elevatorController = new ElevatorController(building)


  describe("ElevatorController") {
    it("initiates with an empty queue") {
      assert(elevatorController.queue.isEmpty)
    }

    it("gets the current elevator floor on initiation") {
      val firstElevator = elevatorController.getCurrentElevatorFloor(0)
      val secondElevator = elevatorController.getCurrentElevatorFloor(1)
      assert(firstElevator==0)
      assert(secondElevator==0)
    }

//    it("moves the elevator") {
//      assert(elevatorController.getCurrentElevatorFloor().floorNumber == 0)
//      elevatorController.moveElevator(building.floors(3))
//      assert(elevatorController.getCurrentElevatorFloor().floorNumber == 3)
//      assert(building.floors(0).elevatorStatus == false
//      )
//    }


    it("adds a Floor to the queue") {
      elevatorController.call(3)
      elevatorController.call(2)
      elevatorController.call(1)
      assert(elevatorController.queue.contains(building.floors(3)))
      assert(elevatorController.queue.contains(building.floors(2)))
      assert(elevatorController.queue.contains(building.floors(1)))
      assert(!elevatorController.queue.contains(building.floors(0)))
      assert(elevatorController.queue == mutable.Queue(building.floors(3), building.floors(2), building.floors(1)))

    }

    it("only has unique calls in the queue, i.e. multiple calls to one floor will not be added to the queue multiple times") {
      elevatorController.call(3)
      elevatorController.call(3)
      elevatorController.call(3)
      assert(elevatorController.queue.size == 1)
      assert(elevatorController.queue == mutable.Queue(building.floors(3)))

    }

//    it("runs through queue and moves the elevator accordingly") {
//      val elevatorController2 = new ElevatorController(building)
//      elevatorController2.call(3)
//      elevatorController2.call(1)
//      assert(elevatorController2.queue.size == 2)
//      assert(elevatorController2.queue == Seq(building.floors(3), building.floors(1)))
//      val output = new java.io.ByteArrayOutputStream()
//      Console.withOut(output) {
//        elevatorController2.initiateElevator()
//      }
//      val terminalOutput = output.toString()
//      assert(terminalOutput.contains("Going to floor 3."))
//      assert(terminalOutput.contains("This is floor 3."))
//      assert(terminalOutput.contains("Going to floor 1."))
//      assert(terminalOutput.contains("This is floor 1."))
//
//      assert(elevatorController2.getCurrentElevatorFloor().floorNumber == 1)
//    }



      //really should do async test here??


    it("gets the closest elevator to the floor call"){
      elevatorController.call(3)
      val floor4 = building.floors.find(_.floorNumber==4).get
      elevatorController.moveElevator(1,floor4)
      //move elevator 0 to floor 4, now elevator 0 is closes to the call and should be returned
      val closestElevatorId =  elevatorController.getClosestElevator()
      assert(closestElevatorId==1)
//      val floor1 = building.floors.find(_.floorNumber==1).get
      //put a call to floor1 into the queue and get the closest elevator (should be the one that hasn't moved i.e. El0.)
      elevatorController.clearElevatorQueue()
      //created new method for clearing queue if just using thee moveElevator method
      elevatorController.call(1)
      val closestElevatoId2 = elevatorController.getClosestElevator()
      assert(closestElevatoId2==0)





    }
    }



  }

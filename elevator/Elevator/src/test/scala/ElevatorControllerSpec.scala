import org.scalatest.funspec.AnyFunSpec

import scala.collection.mutable

class ElevatorControllerSpec extends AnyFunSpec {

  val building = new Building(5)
  val elevatorController = new ElevatorController(building)

  describe("ElevatorController") {
    it("initiates with an empty queue") {
      assert(elevatorController.queue.isEmpty)
    }

    it("gets the current elevator floor on initiation") {
      assert(elevatorController.getCurrentElevatorFloor().floorNumber == 0)
    }

    it("moves the elevator") {
      assert(elevatorController.getCurrentElevatorFloor().floorNumber == 0)
      elevatorController.moveElevator(building.floors(3))
      assert(elevatorController.getCurrentElevatorFloor().floorNumber == 3)
      assert(building.floors(0).elevatorStatus == false
      )
    }


    it("adds a Floor to the queue") {
      elevatorController.call(building.floors(3))
      elevatorController.call(building.floors(2))
      elevatorController.call(building.floors(1))
      assert(elevatorController.queue.contains(building.floors(3)))
      assert(elevatorController.queue.contains(building.floors(2)))
      assert(elevatorController.queue.contains(building.floors(1)))
      assert(!elevatorController.queue.contains(building.floors(0)))
      assert(elevatorController.queue == mutable.Queue(building.floors(3), building.floors(2), building.floors(1)))

    }

    it("only has unique calls in the queue, i.e. multiple calls to one floor will not be added to the queue multiple times") {
      elevatorController.call(building.floors(3))
      elevatorController.call(building.floors(3))
      elevatorController.call(building.floors(3))
      assert(elevatorController.queue.size == 1)
      assert(elevatorController.queue == mutable.Queue(building.floors(3)))

    }

    it("runs through queue and moves the elevator accordingly") {
      val elevatorController2 = new ElevatorController(building)
      elevatorController2.call(building.floors(3))
      elevatorController2.call(building.floors(1))
      assert(elevatorController2.queue.size == 2)
      assert(elevatorController2.queue == Seq(building.floors(3), building.floors(1)))
      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
        elevatorController2.initiateElevator()
      }
      val terminalOutput = output.toString()
      assert(terminalOutput.contains("Going to floor 3."))
      assert(terminalOutput.contains("This is floor 3."))
      assert(terminalOutput.contains("Going to floor 1."))
      assert(terminalOutput.contains("This is floor 1."))

      assert(elevatorController2.getCurrentElevatorFloor().floorNumber == 1)
    }


      //really should do async test here??


    }
  }

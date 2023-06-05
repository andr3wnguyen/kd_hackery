import org.scalatest.funspec.AnyFunSpec

class ElevatorControllerSpec extends AnyFunSpec {

  val building = new Building(5)
  val elevatorController = new ElevatorController(building)

  describe("ElevatorController") {
    it("initiates with an empty queue") {
      assert(elevatorController.queue.isEmpty)

    }
  }
}
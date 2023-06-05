import org.scalatest.funspec.AnyFunSpec


class FloorSpec extends AnyFunSpec {
  val floor1 = new Floor(1)
  val floor0 = new Floor(0)



  describe("Floor") {
    it("create a new floor") {
      assert(floor1.floorNumber == 1)
      assert(floor0.floorNumber == 0)
    }

    it("if a floor is floor 0, the elevatorStatus is true (an elevator starts at floor 0) any other floor has no elevator") {
      assert(floor0.elevatorStatus == true)
      assert(floor1.elevatorStatus == false)
    }

    it("toggles elevatorStatus correctly") {
      assert(floor0.elevatorStatus == true)
      floor0.toggleElevatorStatus()
      assert(floor0.elevatorStatus == false)
    }

  }
}

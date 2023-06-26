import org.scalatest.funspec.AnyFunSpec

class ElevatorSpec extends AnyFunSpec {

  val elevator = new Elevator(1)
  val floor0 = new Floor(0)
  val floor1 = new Floor(1)


  describe("Elevator") {
//    it("can tell what floor it is on") {
//
//      val output = new java.io.ByteArrayOutputStream()
//      Console.withOut(output) {
//      elevator.currentFloor()
//      }
//        val terminalOutput = output.toString()
//      assert(terminalOutput.contains("This is floor 0"))
//
//      }
// *** THE ELEVATOR IS NOT SENTIENT AND HAS NO AWARENESS OF WHAT IT IS AND WHAT IT ISN'T, IT MERELY EXISTS (this test is redundant)
  it("can tell what floor it is going to") {

    val output = new java.io.ByteArrayOutputStream()
    Console.withOut(output) {
      elevator.goingTo(floor1)
    }
    val terminalOutput = output.toString()
    assert(terminalOutput.contains("Going to floor 1"))

  }



}


  }

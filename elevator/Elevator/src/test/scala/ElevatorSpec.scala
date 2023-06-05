import org.scalatest.funspec.AnyFunSpec

class ElevatorSpec extends AnyFunSpec {

  val elevator = new Elevator
  val floor0 = new Floor(0)
  val floor1 = new Floor(1)


  describe("Elevator") {
    it("can tell what floor it is on") {

      val output = new java.io.ByteArrayOutputStream()
      Console.withOut(output) {
      elevator.doorOpen(floor0)
      }
        val terminalOutput = output.toString()
      assert(terminalOutput.contains("This is floor 0"))

      }

  it("can tell what floor it is going to") {

    val output = new java.io.ByteArrayOutputStream()
    Console.withOut(output) {
      elevator.doorClose(floor1)
    }
    val terminalOutput = output.toString()
    assert(terminalOutput.contains("Going to floor 1"))

  }



}


  }

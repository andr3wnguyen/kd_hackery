object run extends App {

  val building = new Building(5)
  val controller = new ElevatorController(building)

  while (true) {
    //activate the elevator - keep this initiateElevator method going ?
    val userInput = scala.io.StdIn.readLine("Enter a value (or 0 to quit): ")
    controller.call(userInput.toInt)
    controller.initiateElevator()

  }






//  val floor3 = controller.convertIntToFloor(3)
//  val floor4 = controller.convertIntToFloor(4)
//
//  controller.call(floor3)
//  controller.call(floor4)
//
//  controller.initiateElevator()
//
//  println("END")
//  println(controller.getCurrentElevatorFloor().floorNumber)
//
//  controller.call(floor3)
//  println(controller.getCurrentElevatorFloor().floorNumber)
//  controller.initiateElevator()
//  println(controller.getCurrentElevatorFloor().floorNumber)
//  println(controller.queue.map(_.floorNumber))
//
//

}

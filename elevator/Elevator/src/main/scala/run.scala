object run extends App {

  val buildingFloors = scala.io.StdIn.readLine("Number of floors in your building: \n").toInt
  val buildingElevators = scala.io.StdIn.readLine("Number of elevators in your building: \n").toInt
  val building = new Building(buildingFloors,buildingElevators)

  val controller = new ElevatorController(building)

  while (true) {
    //activate the elevator - keep this initiateElevator method going ?

    val userInput = scala.io.StdIn.readLine("What floor do you want to go to: \n").trim


    if(userInput!="" && userInput.forall(Character.isDigit) && (0 to buildingFloors).contains(userInput.toInt)) {
      controller.call(userInput.toInt)
      controller.initiateElevator(controller.getClosestElevator())
    }
    else
      println("no that's not a number or floor")


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

object run extends App {

  val buildingFloors = scala.io.StdIn.readLine("Number of floors in your building: \n").toInt
  val buildingElevators = scala.io.StdIn.readLine("Number of elevators in your building: \n").toInt
  val building = new Building(buildingFloors,buildingElevators)
  val controller = new ElevatorController(building)
  val pen = new Draw


  while (true) {
    //activate the elevator - keep this initiateElevator method going ?
    pen.drawBuilding(pen.describeFloors(buildingFloors, controller.elevatorsAndCurrentPositions))

    val userInput = scala.io.StdIn.readLine("What floor do you want to go to: \n").trim

    if(userInput!="" && userInput.forall(Character.isDigit) && (0 to buildingFloors).contains(userInput.toInt)) {
      controller.call(userInput.toInt)
      controller.initiateElevator(controller.getClosestElevator())
      //update the field
      controller.elevatorsAndCurrentPositions = controller.listElevatorsAndCurrentPositions()
    }
    else
      println("no that's not a number or floor")


  }


}

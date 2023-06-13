class Building(numberOfFloors:Int, numberOfElevators:Int) {
  val floors = listOfFloors()
  val elevators = listOfElevators()
//  val elevator = new Elevator


  //create a sequence of Floor items with number of floors passed to the building
  //maps an int to a Floor
  def listOfFloors(): Seq[Floor] = {
    (0 to this.numberOfFloors).map(x => new Floor(x))
  }


  def listOfElevators(): Seq[Elevator] = {
    (0 to this.numberOfElevators-1).map(x => new Elevator(x))
  }

}

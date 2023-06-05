class Building(numberOfFloors:Int) {
  val floors = listOfFloors()
//  val numberOfFloors = numberOfFloors
  val elevator = new Elevator


  //create a sequence of Floor items with number of floors passed to the building
  //maps an int to a Floor
  def listOfFloors(): Seq[Floor] = {
    (0 to this.numberOfFloors).map(x => new Floor(x))
  }



}

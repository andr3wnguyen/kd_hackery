import org.scalatest.funspec.AnyFunSpec


class BuildingSpec extends AnyFunSpec {

val building = new Building(4)
  describe("Building") {


    it("a new building initiates with a list of 4 floors (including floor 0)") {
      assert(building.floors.length == 4)
    }

    }

}

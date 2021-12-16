fun main(args: Array<String>) {
    val squareCabin = SquareCabin(numberOfResidents = 6)
    val roundHut = RoundHut(3)
    val roundTower = RoundTower(4)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
    }
    with(roundHut) {
        println("\nRound Hut\n=========")
        println("Material: $buildingMaterial")
        println("Capacity: $capacity")
        println("Has room? ${hasRoom()}")
    }
    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: $buildingMaterial")
        println("Capacity: $capacity")
        println("Has room? ${hasRoom()}")
    }
}

abstract class Dwelling constructor(private var numberOfResidents : Int) {
    abstract val buildingMaterial : String
    abstract val capacity : Int

     fun hasRoom() = numberOfResidents < capacity
}

class SquareCabin constructor(numberOfResidents : Int) : Dwelling(numberOfResidents) {
    override val buildingMaterial: String = "Wood"
    override val capacity : Int = 6
}

open class RoundHut constructor(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
}

class RoundTower constructor(residents: Int, private val numberOfFloors : Int = 2) : RoundHut(residents) {
    override val buildingMaterial = "Stone"
    override val capacity = super.capacity * this.numberOfFloors
}
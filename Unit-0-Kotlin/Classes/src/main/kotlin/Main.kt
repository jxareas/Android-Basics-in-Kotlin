fun main(args: Array<String>) {
    val myFirstDice = Dice()

    with(myFirstDice)
    {
        println("My dice has $sides sides")
        println("Rolling the dice...Result is: ${roll()}")

        sides = 20
        println("---------------------------------------------------")
        println("My dice now has $sides sides")
        println("Rolling the dice...Result is ${roll()}")
    }


}

class Dice(var sides : Int = 6) {

    fun roll() = (1..sides).random()

}
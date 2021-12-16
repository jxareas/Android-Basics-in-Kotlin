fun main(args: Array<String>) {
    val dice = Dice(6)
    val diceRolled = dice.roll()
    val luckyNumber = 4

    val message = when(diceRolled)
    {
        luckyNumber -> "You Won! You rolled a $luckyNumber"
        1 -> "You rolled a 1. Try again."
        2 -> "You rolled a 2. Try again."
        3 -> "You rolled a 3. Try again."
//        4 -> "You rolled a 4. Try again." Remember 4 is our lucky number
        5 -> "You rolled a 5. Try again."
        6 -> "You rolled a 6. Try again."
        else -> "You lost."
    }
    println(message)
}

class Dice(private val numSides : Int) {


    fun roll() = (1..numSides).random()
}
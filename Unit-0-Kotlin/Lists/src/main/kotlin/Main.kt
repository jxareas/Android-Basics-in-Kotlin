fun main(args: Array<String>) {
    // Creating an immutable list
    val numbers: List<Int> = listOf(1, 2, 3, 4)

    // Printing a read-only list
    println("List of numbers: $numbers")

    // Accessing the first element of a list
    println("First element: ${numbers[0]}")

    //Creating a mutable list
    val mutableNumbers: MutableList<Int> = mutableListOf(1, 2, 3, 4)

    mutableNumbers.addAll(mutableListOf(5, 6, 7, 8))

    //A Printing a  mutable list
    println("List of numbers : $mutableNumbers")

    // Printing elements of a list line by line
    mutableNumbers.forEach(::println)


}
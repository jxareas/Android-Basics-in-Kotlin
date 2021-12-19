fun main() {
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    println("Numbers : $numbers")

    // Sorting a list
    val sortedNumbers = numbers.sorted()
    println("Sorted Numbers : $sortedNumbers")

    // Unique elements in the lists (Converting list to set)
    val numberSet = numbers.toSet()
    println("Unique Numbers : $numberSet")

    // Kotlin Map (Dictionary)
    val ages : MutableMap<String, Int> = mutableMapOf(
        "Fred" to 30,
        "Mark" to 21,
        "Anna" to 33
    )
    println("Names and Ages: $ages")

}
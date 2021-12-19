val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)

val ages : MutableMap<String, Int> = mutableMapOf(
    "Jessica" to 30,
    "Jonathan" to 21,
    "Anna" to 33,
    "Mark" to 28
)

// Printing data element by element with map function
ages
    .map { "${it.key} is ${it.value}" }
    .joinToString(", ")
    .let(::println)

// Obtaining only names with less than 5 characters
ages
    .filter { it.key.length < 5}
    .run{ println(keys)}






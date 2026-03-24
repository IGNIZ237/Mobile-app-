
// T : Comparable<T> est une "Upper Bound Constraint" (Slide 7)
// Cela garantit que l'on peut utiliser les opérateurs de comparaison (>, <) sur T.
fun <T : Comparable<T>> maxOf(list: List<T>): T? {
    if (list.isEmpty()) return null
    
    var max = list[0]
    for (item in list) {
        if (item > max) {
            max = item
        }
    }
    return max
}

fun main() {
    // Test avec des entiers
    val numbers = listOf(3, 7, 2, 9)
    println("Max des nombres : ${maxOf(numbers)}") // Résultat attendu : 9

    // Test avec des chaînes de caractères (ordre alphabétique)
    val fruits = listOf("apple", "banana", "kiwi")
    println("Max des fruits : ${maxOf(fruits)}") // Résultat attendu : kiwi

    // Test avec une liste vide
    println("Max liste vide : ${maxOf(emptyList<Int>())}") // Résultat attendu : null
}

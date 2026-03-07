// Exercice 1 : Modéliser un Zoo
// 1. Classe abstraite avec propriété legs et méthode abstraite
abstract class Animal(val name: String) {
    abstract val legs: Int
    abstract fun makeSound()
}

// 2. Subclasses concrètes
class Dog(name: String) : Animal(name) {
    override val legs = 4
    override fun makeSound() {
        println("$name says Woof!")
    }
}

class Cat(name: String) : Animal(name) {
    override val legs = 4
    override fun makeSound() {
        println("$name says Meow!")
    }
}

fun main() {
    // 3. Liste d'animaux et itération (Polymorphisme)
    val animals = listOf(Dog("Buddy"), Cat("Whiskers"))

    animals.forEach { animal ->
        animal.makeSound()
    }
}
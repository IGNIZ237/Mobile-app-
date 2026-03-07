abstract class Animal(val name: String) {
    abstract val legs: Int
    abstract fun makeSound()
}

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
        val animals = listOf(Dog("Buddy"), Cat("Whiskers"))

    animals.forEach { animal ->
        animal.makeSound()
    }

}

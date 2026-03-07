// Exercice 3 : Formes Dessinables (Interfaces)
// 1. Définition de l'interface
interface Drawable {
    fun draw()
}

// 2. Implémentations avec propriétés spécifiques
class Circle(val radius: Int) : Drawable {
    override fun draw() {
        println("   *** ")
        println(" * * ")
        println("   *** ")
        println("(Circle with radius $radius)")
    }
}

class Square(val side: Int) : Drawable {
    override fun draw() {
        println(" ------- ")
        println("|       |")
        println(" ------- ")
        println("(Square with side $side)")
    }
}

fun main() {
    // Bonus : Liste de Drawable
    val shapes: List<Drawable> = listOf(Circle(5), Square(10))

    shapes.forEach { it.draw() }
}
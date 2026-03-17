/**
 * EXERCICE 2 : Implement a Logger Using Delegation (Advanced Kotlin, Slide 15).
 */

// 1. Définition de l'interface (Contrat)
interface Logger {
    fun log(message: String)
}

// 2. Implémentation pour la Console
class ConsoleLogger : Logger {
    override fun log(message: String) {
        println("Console Log: $message")
    }
}

// 3. Implémentation pour un Fichier (Simulation)
class FileLogger : Logger {
    override fun log(message: String) {
        println("File Log (simulé): Writing '$message' to log.txt")
    }
}

class Application(logger: Logger) : Logger by logger {
    fun start() {
        log("L'application a démarré.") // Appelle automatiquement la méthode du délégué
    }
}

fun main() {
    println("Test avec ConsoleLogger")
    val appWithConsole = Application(ConsoleLogger())
    appWithConsole.start()

    println("\nTest avec FileLogger ")
    val appWithFile = Application(FileLogger())
    appWithFile.start()
}

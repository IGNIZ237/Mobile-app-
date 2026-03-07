// Exercice 2 : État de Requête Réseau (Sealed Class)
// 1. Définition de la Sealed Class
sealed class NetworkState {
    object Loading : NetworkState()
    data class Success(val data: String) : NetworkState()
    data class Error(val message: String) : NetworkState()
}

// 2. Fonction de gestion d'état avec 'when' exhaustif
fun handleState(state: NetworkState) {
    when (state) {
        is NetworkState.Loading -> println("Loading...")
        is NetworkState.Success -> println("Data: ${state.data}")
        is NetworkState.Error -> println("Error: ${state.message}")
    }
}

fun main() {
    val states = listOf(
        NetworkState.Loading,
        NetworkState.Success("User data loaded"),
        NetworkState.Error("Network timeout")
    )
    
    states.forEach { handleState(it) }
}
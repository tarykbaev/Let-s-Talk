package kg.turar.arykbaev.core.domain.model

data class User(
    val name: String,
    val email: String,
    val state: String,
    val imgUrl: String,
    val department: String
)
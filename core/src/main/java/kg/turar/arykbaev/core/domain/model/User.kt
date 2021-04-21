package kg.turar.arykbaev.core.domain.model

import java.io.Serializable

data class User(
    val name: String,
    val email: String,
    val password: String,
    val state: String,
    val imgUrl: String,
    val department: String
): Serializable
package kg.turar.arykbaev.letstalk.domain

enum class UserState(val state: String) {
    ONLINE("online"),
    OFFLINE("offline"),
    TYPING("typing"),
}
package kg.turar.arykbaev.letstalk.domain

sealed class Event {
    class Notification(val message: String) : Event()
    class Success(): Event()
}
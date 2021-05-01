package kg.turar.arykbaev.letstalk.domain

sealed class Event {
    class Notification(val message: String) : Event()
    class Success(val data: Any? = null): Event()
    class Unauthorized() : Event()
    class Loading(): Event()
}
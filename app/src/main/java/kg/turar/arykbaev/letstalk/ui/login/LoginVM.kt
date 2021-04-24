package kg.turar.arykbaev.letstalk.ui.login


import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class LoginVM @Inject constructor(private val repository: AuthAppRepository) : BaseVM() {

    init {
        event = repository.getEvent()
    }

    fun login(email: String, password: String) {
        repository.login(email, password)
    }
}
package kg.turar.arykbaev.letstalk.ui

import com.firebase.ui.database.FirebaseRecyclerOptions
import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class MainVM @Inject constructor(private val repository: AuthAppRepository) : BaseVM() {

    lateinit var currentUser: User

    init {
        event = repository.getEvent()
    }

    fun initUser() {
        repository.initUser()
    }

    fun logout() {
        repository.logout()
    }

    fun checkAuthentication() {
        repository.checkAuthentication()
    }

    fun getRecyclerOptions(): FirebaseRecyclerOptions<User> {
        return repository.getRecyclerOptions()
    }
}
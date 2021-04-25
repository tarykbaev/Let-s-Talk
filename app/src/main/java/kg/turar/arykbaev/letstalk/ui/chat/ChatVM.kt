package kg.turar.arykbaev.letstalk.ui.chat

import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class ChatVM @Inject constructor(private val repository: AuthAppRepository) : BaseVM() {

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

}
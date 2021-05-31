package kg.turar.arykbaev.letstalk.ui.chat

import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.data.repository.SearchRepository
import kg.turar.arykbaev.letstalk.domain.UserState
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class ChatVM @Inject constructor(
    private val repository: AuthAppRepository,
    private val searchRepository: SearchRepository
) : BaseVM() {

    lateinit var currentUser: User
    val user = searchRepository.users
    val chatUser = searchRepository.chatUser

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

    fun changeUserState(state: UserState) {
        repository.changeUserState(state)
    }

    fun fetchUser(count: Int) {
        searchRepository.fetchUsers(count)
    }

    fun fetchChatUser(count: Int) {
        searchRepository.fetchChatUser(count)
    }
}
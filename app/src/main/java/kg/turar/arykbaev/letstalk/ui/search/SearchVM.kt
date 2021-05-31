package kg.turar.arykbaev.letstalk.ui.search

import kg.turar.arykbaev.letstalk.data.repository.SearchRepository
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class SearchVM @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseVM() {

    lateinit var currentUser: User
    val user = searchRepository.users


    fun fetchUser(count: Int) {
        searchRepository.fetchUsers(count)
    }

    fun fetchChatUser(count: Int) {
        searchRepository.fetchChatUser(count)
    }
}
package kg.turar.arykbaev.letstalk.ui.message


import kg.turar.arykbaev.letstalk.data.repository.MessageRepository
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class MessageVM @Inject constructor(
    private val messageRepository: MessageRepository
) : BaseVM() {
    lateinit var user: User

    fun getUserId() = user.id

    fun sendMessage(message: String, userId: String, type: String) {
        messageRepository.sendMessage(message, userId, type)
    }
}
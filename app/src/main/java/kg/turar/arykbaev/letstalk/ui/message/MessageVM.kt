package kg.turar.arykbaev.letstalk.ui.message


import androidx.lifecycle.MutableLiveData
import kg.turar.arykbaev.letstalk.data.repository.MessageRepository
import kg.turar.arykbaev.letstalk.domain.models.Message
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class MessageVM @Inject constructor(
    private val messageRepository: MessageRepository
) : BaseVM() {

    lateinit var user: User
    val message: MutableLiveData<Message> = messageRepository.message
    lateinit var correctionMessage: Message

    fun getMessageText(): String = correctionMessage.text

    fun sendMessage(message: String, type: String) {
        messageRepository.sendMessage(message, user.id, type)
    }

    fun fetchMessage(countMessage: Int) {
        messageRepository.fetchMessage(user.id, countMessage)
    }
}
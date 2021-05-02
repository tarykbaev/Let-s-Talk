package kg.turar.arykbaev.letstalk.ui.message


import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class MessageVM @Inject constructor() : BaseVM() {
    lateinit var user: User
}
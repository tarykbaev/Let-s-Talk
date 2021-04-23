package kg.turar.arykbaev.letstalk.ui.sign_up

import androidx.lifecycle.LiveData
import kg.turar.arykbaev.letstalk.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class SignUpVM @Inject constructor(private val repository: AuthAppRepository) : BaseVM() {


    init {
        event = repository.getEvent()
    }

    fun isNewEmail(email: String): LiveData<Boolean> = repository.isNewEmail(email)
}
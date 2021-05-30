package kg.turar.arykbaev.letstalk.ui.person

import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.data.repository.ProfileRepository
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class PersonVM @Inject constructor(
    private val authRepository: AuthAppRepository,
    private val profileRepository: ProfileRepository
) : BaseVM() {

    fun logout() = authRepository.logout()
}
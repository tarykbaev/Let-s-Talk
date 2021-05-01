package kg.turar.arykbaev.letstalk.ui.sign_up

import kg.turar.arykbaev.letstalk.data.repository.AuthAppRepository
import kg.turar.arykbaev.letstalk.data.source.Countries
import kg.turar.arykbaev.letstalk.data.source.Languages
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.ui.base.BaseVM
import javax.inject.Inject

class SignUpVM @Inject constructor(private val repository: AuthAppRepository) : BaseVM() {

    lateinit var user: User

    init {
        event = repository.getEvent()
    }

    fun checkEmail(email: String) = repository.checkEmail(email)

    fun allDatesValid(): Boolean {
        user.run { return uri != null && gender.isNotEmpty() && department.isNotEmpty() && grade.isNotEmpty() }
    }

    fun allSelected(): Boolean {
        user.run { return from.isNotEmpty() && nativeLang.isNotEmpty() && learningLang.isNotEmpty() }
    }

    fun register(user: User) {
        repository.register(user)
    }

    fun getCountries() = Countries.countries

    fun getLanguages() = Languages.languages
}
package kg.turar.arykbaev.letstalk.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kg.turar.arykbaev.letstalk.ui.chat.ChatVM
import kg.turar.arykbaev.letstalk.ui.login.LoginVM
import kg.turar.arykbaev.letstalk.ui.person.ProfileVM
import kg.turar.arykbaev.letstalk.ui.search.SearchVM
import kg.turar.arykbaev.letstalk.ui.sign_up.SignUpVM
import javax.inject.Singleton

@Module
abstract class VMModule {

    @Binds
    @IntoMap
    @VMKey(ChatVM::class)
    abstract fun bindChatVM(splashVM: ChatVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(LoginVM::class)
    abstract fun bindLoginVM(loginVM: LoginVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(ProfileVM::class)
    abstract fun bindProfileVM(profileVM: ProfileVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(SearchVM::class)
    abstract fun bindSearchVM(searchVM: SearchVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(SignUpVM::class)
    @Singleton
    abstract fun bindSignUpVM(signUpVM: SignUpVM): ViewModel
}
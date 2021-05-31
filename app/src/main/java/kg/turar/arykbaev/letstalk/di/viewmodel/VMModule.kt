package kg.turar.arykbaev.letstalk.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kg.turar.arykbaev.letstalk.ui.MainVM
import kg.turar.arykbaev.letstalk.ui.chat.ChatVM
import kg.turar.arykbaev.letstalk.ui.login.LoginVM
import kg.turar.arykbaev.letstalk.ui.message.MessageVM
import kg.turar.arykbaev.letstalk.ui.person.PersonVM
import kg.turar.arykbaev.letstalk.ui.search.SearchVM
import kg.turar.arykbaev.letstalk.ui.sign_up.SignUpVM
import javax.inject.Singleton

@Module
abstract class VMModule {

    @Binds
    @IntoMap
    @VMKey(LoginVM::class)
    abstract fun bindLoginVM(loginVM: LoginVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(SignUpVM::class)
    abstract fun bindSignUpVM(signUpVM: SignUpVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(MainVM::class)
    @Singleton
    abstract fun bindMainVM(mainVM: MainVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(MessageVM::class)
    abstract fun bindMessageVM(messageVM: MessageVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(PersonVM::class)
    abstract fun bindPersonVM(personVM: PersonVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(ChatVM::class)
    abstract fun bindChatVM(chatVM: ChatVM): ViewModel

    @Binds
    @IntoMap
    @VMKey(SearchVM::class)
    abstract fun bindSearchVM(searchVM: SearchVM): ViewModel
}
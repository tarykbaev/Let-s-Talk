package kg.turar.arykbaev.letstalk.di

import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.ui.MainActivity
import kg.turar.arykbaev.letstalk.ui.chat.ChatFragment
import kg.turar.arykbaev.letstalk.ui.login.LoginFragment
import kg.turar.arykbaev.letstalk.ui.message.MessageFragment
import kg.turar.arykbaev.letstalk.ui.person.PersonFragment
import kg.turar.arykbaev.letstalk.ui.search.SearchFragment
import kg.turar.arykbaev.letstalk.ui.sign_up.StepOneFragment
import kg.turar.arykbaev.letstalk.ui.sign_up.StepThreeFragment
import kg.turar.arykbaev.letstalk.ui.sign_up.StepTwoFragment
import kg.turar.arykbaev.letstalk.ui.sign_up.VerifyMailFragment

interface ScreenInjector {
    fun inject(application: App)
    fun inject(activity: MainActivity)
    fun inject(fragment: ChatFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: PersonFragment)
    fun inject(fragment: StepOneFragment)
    fun inject(fragment: StepTwoFragment)
    fun inject(fragment: StepThreeFragment)
    fun inject(fragment: VerifyMailFragment)
    fun inject(fragment: MessageFragment)
}
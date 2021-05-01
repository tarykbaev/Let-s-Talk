package kg.turar.arykbaev.letstalk.di

import dagger.Component
import kg.turar.arykbaev.letstalk.di.viewmodel.VMFactoryModule
import kg.turar.arykbaev.letstalk.di.viewmodel.VMModule
import javax.inject.Singleton

@Singleton
@Component(modules = [VMFactoryModule::class, VMModule::class, ApplicationModule::class, FirebaseModule::class])
interface AppComponent : ScreenInjector
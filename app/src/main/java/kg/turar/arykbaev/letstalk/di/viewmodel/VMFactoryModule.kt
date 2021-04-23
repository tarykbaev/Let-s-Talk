package kg.turar.arykbaev.letstalk.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class VMFactoryModule {
    @Binds
    abstract fun bindVMFactory(factory: VMFactory): ViewModelProvider.Factory
}
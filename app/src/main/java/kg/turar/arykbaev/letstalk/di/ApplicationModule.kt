package kg.turar.arykbaev.letstalk.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kg.turar.arykbaev.letstalk.App
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Provides @Singleton fun provideApplicationContext(): Context = application
}
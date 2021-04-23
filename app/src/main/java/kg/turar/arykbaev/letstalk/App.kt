package kg.turar.arykbaev.letstalk

import android.app.Application
import kg.turar.arykbaev.letstalk.di.AppComponent
import kg.turar.arykbaev.letstalk.di.ApplicationModule
import kg.turar.arykbaev.letstalk.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
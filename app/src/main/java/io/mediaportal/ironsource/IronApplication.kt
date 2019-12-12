package io.mediaportal.ironsource

import android.app.Application
import io.mediaportal.ironsource.di.AppComponent
import io.mediaportal.ironsource.di.AppModule
import io.mediaportal.ironsource.di.DaggerAppComponent


class IronApplication : Application() {

    companion object {
        lateinit var instance: IronApplication
    }

    val component: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
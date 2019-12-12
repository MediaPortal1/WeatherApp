package io.mediaportal.ironsource.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import io.mediaportal.ironsource.MainActivity
import javax.inject.Singleton


@ApplicationScope
@Component(modules = [AppModule::class, IOModule::class])
interface AppComponent {
    fun getApplicationContext(): Context
    fun inject(mainActivity: MainActivity)
}
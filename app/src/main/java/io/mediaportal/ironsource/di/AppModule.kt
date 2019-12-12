package io.mediaportal.ironsource.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.mediaportal.ironsource.IronApplication
import io.mediaportal.ironsource.MainPresenter

@Module
class AppModule(private val app: IronApplication) {
    @ApplicationScope
    @Provides
    internal fun provideApplicationContext(): Context = app.applicationContext

}
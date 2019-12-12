package io.mediaportal.ironsource.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.mediaportal.ironsource.ButtonActionEndpoint
import io.mediaportal.ironsource.IronApplication
import io.mediaportal.ironsource.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class IOModule {

    private val gson: Gson = GsonBuilder().create()

    private val okHttp = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(IronApplication.instance.getString(R.string.base_url))
        .client(okHttp)
        .build()

    private val buttonActionEndpoint = retrofit.create(ButtonActionEndpoint::class.java)

    private val networkCoroutine = CoroutineScope(Dispatchers.IO)

    private val uiCoroutine = CoroutineScope(Dispatchers.Main)

    @Provides
    internal fun provideGson(): Gson = gson

    @Provides
    internal fun provideOkHttp(): OkHttpClient = okHttp

    @Provides
    internal fun provideRetrofit(): Retrofit = retrofit

    @Provides
    internal fun provideButtonAsActionEndpoint(): ButtonActionEndpoint = buttonActionEndpoint

    @Provides
    @Named("network")
    internal fun provideNetworkScope(): CoroutineScope = networkCoroutine

    @Provides
    @Named("ui")
    internal fun provideUIScope(): CoroutineScope = uiCoroutine
}
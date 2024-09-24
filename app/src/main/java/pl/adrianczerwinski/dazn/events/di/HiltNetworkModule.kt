package pl.adrianczerwinski.dazn.events.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.adrianczerwinski.dazn.events.network.EventsClient
import pl.adrianczerwinski.dazn.events.network.EventsNetworkValues.BASE_URL
import pl.adrianczerwinski.dazn.events.network.EventsNetworkValues.CONNECTION_TIMEOUT
import pl.adrianczerwinski.dazn.events.network.EventsNetworkValues.READ_TIMEOUT
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HiltNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideEventsClient(retrofit: Retrofit): EventsClient = retrofit.create(EventsClient::class.java)
}

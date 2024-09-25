package pl.adrianczerwinski.dazn.events.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.adrianczerwinski.dazn.events.network.EventsClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltEventsNetworkModule {

    @Provides
    @Singleton
    fun provideEventsClient(retrofit: Retrofit): EventsClient {
        return retrofit.create(EventsClient::class.java)
    }
}
package pl.adrianczerwinski.dazn.schedule.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.adrianczerwinski.dazn.schedule.network.ScheduleClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltScheduleNetworkModule {

    @Provides
    @Singleton
    fun provideScheduleClient(retrofit: Retrofit): ScheduleClient {
        return retrofit.create(ScheduleClient::class.java)
    }
}
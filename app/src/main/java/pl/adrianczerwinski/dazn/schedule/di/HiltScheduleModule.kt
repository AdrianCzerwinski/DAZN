package pl.adrianczerwinski.dazn.schedule.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.adrianczerwinski.dazn.schedule.data.repository.ScheduleRepository
import pl.adrianczerwinski.dazn.schedule.data.repository.ScheduleRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
interface HiltScheduleModule {

    @Binds
    fun scheduledEventsRepository(impl: ScheduleRepositoryImpl): ScheduleRepository
}

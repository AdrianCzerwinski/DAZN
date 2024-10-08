package pl.adrianczerwinski.dazn.events.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.adrianczerwinski.dazn.events.data.repository.EventsRepository
import pl.adrianczerwinski.dazn.events.data.repository.EventsRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
interface HiltEventsModule {

    @Binds
    fun eventsRepository(impl: EventsRepositoryImpl): EventsRepository
}

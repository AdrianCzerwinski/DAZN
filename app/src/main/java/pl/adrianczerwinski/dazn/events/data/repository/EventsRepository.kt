package pl.adrianczerwinski.dazn.events.data.repository

import pl.adrianczerwinski.dazn.events.domain.model.Event

interface EventsRepository {
    suspend fun getEvents(): Result<List<Event>>
}

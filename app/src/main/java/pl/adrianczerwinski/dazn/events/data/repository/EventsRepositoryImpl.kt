package pl.adrianczerwinski.dazn.events.data.repository

import pl.adrianczerwinski.dazn.events.data.mapper.EventsMapper
import pl.adrianczerwinski.dazn.events.network.EventsClient
import pl.adrianczerwinski.dazn.events.domain.model.Event
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val restClient: EventsClient,
    private val mapper: EventsMapper
) : EventsRepository {

    override suspend fun getEvents(): Result<List<Event>> = kotlin.runCatching {
        restClient.getEvents()
    }.fold(
        onSuccess = { events -> Result.success(mapper.mapToEvents(events)) },
        onFailure = { error -> Result.failure(error) }
    )
}

package pl.adrianczerwinski.dazn.events.domain.usecase

import pl.adrianczerwinski.dazn.events.data.repository.EventsRepository
import pl.adrianczerwinski.dazn.events.domain.model.Event
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {
    suspend operator fun invoke(): List<Event>? = eventsRepository.getEvents().getOrNull()
}

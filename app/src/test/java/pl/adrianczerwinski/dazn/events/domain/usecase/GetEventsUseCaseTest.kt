package pl.adrianczerwinski.dazn.events.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.adrianczerwinski.dazn.events.data.repository.EventsRepository
import pl.adrianczerwinski.dazn.events.domain.model.Event

class GetEventsUseCaseTest {
    private val eventsRepository = mockk<EventsRepository>()
    private val getEventsUseCase = GetEventsUseCase(eventsRepository)

    @Test
    fun `should return events from repository when repository returns success`() = runTest {
        // given
        val events = listOf(mockk<Event>())
        coEvery { eventsRepository.getEvents() } returns Result.success(events)

        // when
        val result = getEventsUseCase()

        // then
        assertEquals(events, result)
    }

    @Test
    fun `should return null when repository returns failure`() = runTest {
        // given
        coEvery { eventsRepository.getEvents() } returns Result.failure(Exception())

        // when
        val result = getEventsUseCase()

        // then
        assertEquals(null, result)
    }
}
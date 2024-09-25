package pl.adrianczerwinski.dazn.events.data.repository

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.events.data.mapper.EventsMapper
import pl.adrianczerwinski.dazn.events.data.model.EventResponse
import pl.adrianczerwinski.dazn.events.domain.model.Event
import pl.adrianczerwinski.dazn.events.network.EventsClient

class EventsRepositoryImplTest {
    private val restClient: EventsClient = mockk()
    private val mapper: EventsMapper = mockk()
    private val eventResponse = EventResponse(
        id = "id",
        title = "title",
        subtitle = "subtitle",
        date = "date",
        imageUrl = "imageUrl",
        videoUrl = "videoUrl"
    )
    private val event = with(eventResponse) {
        Event(
            id = id,
            date = Date(
                date = date,
                time = "time",
                type = DateType.STANDARD
            ),
            title = title,
            imageUrl = imageUrl,
            videoUrl = videoUrl,
            subtitle = subtitle
        )
    }

    private val repository = EventsRepositoryImpl(restClient, mapper)

    @Test
    fun `getEvents should return success when request succeeds`() = runTest {
        // given
        val events = listOf(eventResponse)
        val expected = listOf(event)
        coEvery { restClient.getEvents() } returns events
        every { mapper.mapToEvents(events) } returns expected

        // when
        val result = repository.getEvents()

        // then
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `getEvents should return failure when request fails`() = runTest {
        // given
        coEvery { restClient.getEvents() } throws Exception()

        // when
        val result = repository.getEvents()

        // then
        assertTrue(result.isFailure)
    }
}
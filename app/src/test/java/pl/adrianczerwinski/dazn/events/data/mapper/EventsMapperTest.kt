package pl.adrianczerwinski.dazn.events.data.mapper

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.adrianczerwinski.dazn.common.helpers.DateTimeFormatter
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.events.data.model.EventResponse
import pl.adrianczerwinski.dazn.events.domain.model.Event

class EventsMapperTest {
    private val date = "date"
    private val time = "time"
    private val formattedDate = Date(date, time, DateType.STANDARD)
    private val dateTimeFormatter = mockk<DateTimeFormatter>()
    private val mapper = EventsMapper(dateTimeFormatter)

    @Test
    fun `mapToEvents should return list of events`() {
        // given
        val eventResponse = listOf(
            EventResponse(
                id ="id",
                date = date,
                title = "title",
                imageUrl = "imageUrl",
                videoUrl = "videoUrl",
                subtitle = "subtitle"
            )
        )
        val expected = with(eventResponse.first()) {
            Event(
                id = id,
                date = formattedDate,
                title = title,
                imageUrl = imageUrl,
                videoUrl = videoUrl,
                subtitle = subtitle
            )
        }
        every { dateTimeFormatter.formatDate(date) } returns date
        every { dateTimeFormatter.formatTime(date) } returns time
        every { dateTimeFormatter.getDateType(date) } returns DateType.STANDARD

        // when
        val result = mapper.mapToEvents(eventResponse)

        // then
        assertEquals(expected, result.first())
    }
}
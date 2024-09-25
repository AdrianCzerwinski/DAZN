package pl.adrianczerwinski.dazn.events.data.mapper

import pl.adrianczerwinski.dazn.common.helpers.DateTimeFormatter
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.events.data.model.EventResponse
import pl.adrianczerwinski.dazn.events.domain.model.Event
import javax.inject.Inject

class EventsMapper @Inject constructor(
    private val dateTimeFormatter: DateTimeFormatter
) {
    fun mapToEvents(eventResponse: List<EventResponse>) = eventResponse.map { event ->
        with(event) {
            Event(
                id = id,
                date = getDate(date),
                title = title,
                imageUrl = imageUrl,
                videoUrl = videoUrl,
                subtitle = subtitle
            )
        }
    }.sortedBy { it.date.time }

    private fun getDate(date: String): Date {
        return Date(
            date = dateTimeFormatter.formatDate(date),
            time = dateTimeFormatter.formatTime(date),
            type = dateTimeFormatter.getDateType(date)
        )
    }
}

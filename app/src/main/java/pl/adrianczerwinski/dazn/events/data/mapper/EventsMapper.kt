package pl.adrianczerwinski.dazn.events.data.mapper

import pl.adrianczerwinski.dazn.common.helpers.DateTimeFormatter
import pl.adrianczerwinski.dazn.common.helpers.DateTimeFormatter.Companion.TEXT_YEAR_MONTH_DAY_HOUR_MINUTE
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
                date = dateTimeFormatter.formatDate(date, TEXT_YEAR_MONTH_DAY_HOUR_MINUTE),
                title = title,
                imageUrl = imageUrl,
                videoUrl = videoUrl,
                subtitle = subtitle
            )
        }
    }
}

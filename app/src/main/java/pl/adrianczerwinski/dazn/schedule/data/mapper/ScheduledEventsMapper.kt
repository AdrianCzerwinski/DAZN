package pl.adrianczerwinski.dazn.schedule.data.mapper

import pl.adrianczerwinski.dazn.common.helpers.DateTimeFormatter
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.schedule.data.model.ScheduledEventResponse
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import javax.inject.Inject

class ScheduledEventsMapper @Inject constructor(
    private val dateTimeFormatter: DateTimeFormatter
) {
    fun mapToUpcomingEvents(scheduledEventsResponse: List<ScheduledEventResponse>) =
        scheduledEventsResponse.map { event ->
            with(event) {
                ScheduledEvent(
                    id = id,
                    date = getDate(date),
                    title = title,
                    imageUrl = imageUrl,
                    subtitle = subtitle
                )
            }
        }.filter { it.date.type == DateType.TOMORROW }.sortedBy { it.date.time }

    private fun getDate(date: String): Date {
        return Date(
            date = dateTimeFormatter.formatDate(date),
            time = dateTimeFormatter.formatTime(date),
            type = dateTimeFormatter.getDateType(date)
        )
    }
}

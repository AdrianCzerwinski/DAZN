package pl.adrianczerwinski.dazn.common.helpers

import pl.adrianczerwinski.dazn.common.models.DateType
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateTimeFormatter @Inject constructor(
    private val locale: Locale
) {
    fun formatDate(dateTimeString: String): String {
        val formatter = DateTimeFormatter.ofPattern(TEXT_YEAR_MONTH_DAY, locale)
        val dateTime = OffsetDateTime.parse(dateTimeString)
        return dateTime.format(formatter)
    }

    fun formatTime(dateTimeString: String): String {
        val dateTime = OffsetDateTime.parse(dateTimeString)
        return dateTime.format(DateTimeFormatter.ofPattern(HOURS_MINUTES, locale))
    }

    fun getDateType(dateTimeString: String): DateType {
        val dateTime = OffsetDateTime.parse(dateTimeString)
        return when {
            isToday(dateTime) -> DateType.TODAY
            isYesterday(dateTime) -> DateType.YESTERDAY
            isTomorrow(dateTime) -> DateType.TOMORROW
            else -> DateType.STANDARD
        }
    }

    private fun isToday(dateTime: OffsetDateTime): Boolean {
        val today = OffsetDateTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS).dayOfYear
        return dateTime.truncatedTo(ChronoUnit.DAYS).dayOfYear == today
    }

    private fun isYesterday(dateTime: OffsetDateTime): Boolean {
        val yesterday = OffsetDateTime.now(ZoneId.systemDefault()).minusDays(1).truncatedTo(ChronoUnit.DAYS).dayOfYear
        return dateTime.truncatedTo(ChronoUnit.DAYS).dayOfYear == yesterday
    }

    private fun isTomorrow(dateTime: OffsetDateTime): Boolean {
        val tomorrow = OffsetDateTime.now(ZoneId.systemDefault()).plusDays(1).truncatedTo(ChronoUnit.DAYS).dayOfYear
        return dateTime.truncatedTo(ChronoUnit.DAYS).dayOfYear == tomorrow
    }

    companion object {
        const val TEXT_YEAR_MONTH_DAY = "yyyy-MM-dd"
        const val HOURS_MINUTES = "HH:mm"
    }
}

package pl.adrianczerwinski.dazn.common.helpers

import android.icu.text.SimpleDateFormat
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateTimeFormatter @Inject constructor(
    private val locale: Locale
) {
    fun formatDate(dateTimeString: String, format: String): String {
        val formatter = DateTimeFormatter.ofPattern(format, locale)
        return runCatching {
            OffsetDateTime.parse(dateTimeString).format(formatter)
        }.getOrDefault("")
    }

    companion object {
        const val TEXT_YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm"
    }
}
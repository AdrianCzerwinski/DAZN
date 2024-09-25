package pl.adrianczerwinski.dazn.common.helpers

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import pl.adrianczerwinski.dazn.common.models.DateType
import java.time.OffsetDateTime
import java.util.Locale

class DateTimeFormatterTest {
    private val locale = Locale.ENGLISH
    private val currentDate = mockk<CurrentDate>()
    private val dateTimeFormatter = DateTimeFormatter(locale, currentDate)
    private val dateTimeString = "2021-07-01T18:00:00Z"

    @Test
    fun `formatDate should return proper date`() {
        val expected = "2021-07-01"

        val result = dateTimeFormatter.formatDate(dateTimeString)

        assertEquals(expected, result)
    }

    @Test
    fun `formatTime should return proper time`() {
        val expected = "18:00"

        val result = dateTimeFormatter.formatTime(dateTimeString)

        assertEquals(expected, result)
    }

    @Test
    fun `getDateType should TODAY when date is today`() {
        every { currentDate.getCurrentDate() } returns OffsetDateTime.parse(dateTimeString)

        val result = dateTimeFormatter.getDateType(dateTimeString)

        assertEquals(DateType.TODAY, result)
    }

    @Test
    fun `getDateType should YESTERDAY when date is yesterday`() {
        every { currentDate.getCurrentDate() } returns OffsetDateTime.parse("2021-07-02T18:00:00Z")

        val result = dateTimeFormatter.getDateType(dateTimeString)

        assertEquals(DateType.YESTERDAY, result)
    }

    @Test
    fun `getDateType should TOMORROW when date is tomorrow`() {
        every { currentDate.getCurrentDate() } returns OffsetDateTime.parse("2021-06-30T18:00:00Z")

        val result = dateTimeFormatter.getDateType(dateTimeString)

        assertEquals(DateType.TOMORROW, result)
    }

    @Test
    fun `getDateType should STANDARD when date is not today, yesterday or tomorrow`() {
        every { currentDate.getCurrentDate() } returns OffsetDateTime.parse("2021-08-01T18:00:00Z")

        val result = dateTimeFormatter.getDateType(dateTimeString)

        assertEquals(DateType.STANDARD, result)
    }
}
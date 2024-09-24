package pl.adrianczerwinski.dazn.schedule.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduledEventResponse(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String
)

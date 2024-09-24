package pl.adrianczerwinski.dazn.events.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResponse(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String
)

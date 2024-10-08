package pl.adrianczerwinski.dazn.events.domain.model

import pl.adrianczerwinski.dazn.common.models.Date

data class Event(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: Date,
    val imageUrl: String,
    val videoUrl: String
)

package pl.adrianczerwinski.dazn.schedule.domain.model

import pl.adrianczerwinski.dazn.common.models.Date

data class ScheduledEvent(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: Date,
    val imageUrl: String
)

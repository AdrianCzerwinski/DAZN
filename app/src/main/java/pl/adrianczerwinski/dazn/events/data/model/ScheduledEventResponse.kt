package pl.adrianczerwinski.dazn.events.data.model

data class ScheduledEventResponse(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String
)

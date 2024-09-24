package pl.adrianczerwinski.dazn.events.data.model

data class EventResponse(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String
)

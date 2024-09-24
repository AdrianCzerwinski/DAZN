package pl.adrianczerwinski.dazn.events.domain.model

data class Event(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String
)

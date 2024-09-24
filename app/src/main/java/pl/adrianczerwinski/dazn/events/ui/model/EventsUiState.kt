package pl.adrianczerwinski.dazn.events.ui.model

import pl.adrianczerwinski.dazn.events.domain.model.Event

data class EventsUiState(
    val events: List<Event> = listOf(),
    val screenState: ScreenState = ScreenState.LOADING
)

enum class ScreenState { CONTENT, LOADING, ERROR, EMPTY }

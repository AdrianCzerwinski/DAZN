package pl.adrianczerwinski.dazn.events.ui.model

import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.events.domain.model.Event

data class EventsUiState(
    val events: List<Event> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)



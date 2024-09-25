package pl.adrianczerwinski.dazn.schedule.ui.model

import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent

data class ScheduleUiState(
    val events: List<ScheduledEvent> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)

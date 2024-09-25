package pl.adrianczerwinski.dazn.schedule.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.common.views.CommonErrorScreen
import pl.adrianczerwinski.dazn.common.views.CommonLoadingScreen
import pl.adrianczerwinski.dazn.common.views.LightDarkPreview
import pl.adrianczerwinski.dazn.common.views.ScreenPreview
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import pl.adrianczerwinski.dazn.schedule.ui.components.ScheduleEventItem
import pl.adrianczerwinski.dazn.schedule.ui.model.ScheduleUiState

@Composable
fun ScheduleScreen(
    state: ScheduleUiState,
    onRetrySchedule: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state.screenState) {
            ScreenState.CONTENT -> ScheduleList(state.events)

            ScreenState.LOADING -> CommonLoadingScreen()

            ScreenState.ERROR -> CommonErrorScreen { onRetrySchedule() }

            ScreenState.EMPTY -> CommonErrorScreen()
        }
    }
}

@Composable
private fun ScheduleList(eventsList: List<ScheduledEvent>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(eventsList.size) { index ->
            ScheduleEventItem(
                event = eventsList[index]
            )
        }
    }
}

@LightDarkPreview
@Composable
fun ScheduleScreenPreview() = ScreenPreview {
    ScheduleScreen(
        state = ScheduleUiState(
            screenState = ScreenState.CONTENT,
            events = listOf(
                ScheduledEvent(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    subtitle = "subtitle",
                ),
                ScheduledEvent(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    subtitle = "subtitle",
                ),
                ScheduledEvent(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    subtitle = "subtitle",
                ),
            )
        ),
        onRetrySchedule = {}
    )
}

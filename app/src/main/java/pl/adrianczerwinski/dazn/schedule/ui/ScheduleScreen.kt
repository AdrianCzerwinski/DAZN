package pl.adrianczerwinski.dazn.schedule.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.common.views.CommonError
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

            ScreenState.LOADING -> Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }

            ScreenState.ERROR -> Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CommonError { onRetrySchedule() }
            }

            ScreenState.EMPTY -> {}
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

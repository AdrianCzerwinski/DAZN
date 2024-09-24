package pl.adrianczerwinski.dazn.events.ui

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
import pl.adrianczerwinski.dazn.common.views.CommonError
import pl.adrianczerwinski.dazn.events.domain.model.Event
import pl.adrianczerwinski.dazn.events.ui.components.EventItem
import pl.adrianczerwinski.dazn.events.ui.model.EventsUiState
import pl.adrianczerwinski.dazn.events.ui.model.ScreenState

@Composable
fun EventsScreen(
    state: EventsUiState,
    onEventClick: (String) -> Unit,
    onRetryEvents: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state.screenState) {
            ScreenState.CONTENT -> EventsList(state.events) { onEventClick(it) }

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
                CommonError { onRetryEvents() }
            }

            ScreenState.EMPTY -> {}
        }
    }
}

@Composable
private fun EventsList(
    eventsList: List<Event>,
    onEventClick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(eventsList.size) { index ->
            EventItem(
                event = eventsList[index],
                onEventClick = onEventClick
            )
        }
    }
}

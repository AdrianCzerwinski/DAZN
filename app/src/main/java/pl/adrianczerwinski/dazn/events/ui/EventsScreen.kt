package pl.adrianczerwinski.dazn.events.ui

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
import pl.adrianczerwinski.dazn.events.domain.model.Event
import pl.adrianczerwinski.dazn.events.ui.components.EventItem
import pl.adrianczerwinski.dazn.events.ui.model.EventsUiState

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

            ScreenState.LOADING -> CommonLoadingScreen()

            ScreenState.ERROR -> CommonErrorScreen { onRetryEvents() }

            ScreenState.EMPTY -> CommonErrorScreen()
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

@LightDarkPreview
@Composable
fun EventsScreenPreview() = ScreenPreview {
    EventsScreen(
        state = EventsUiState(
            screenState = ScreenState.CONTENT,
            events = listOf(
                Event(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    videoUrl = "https://www.youtube.com/watch?v=123",
                    subtitle = "subtitle",
                ),
                Event(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    videoUrl = "https://www.youtube.com/watch?v=123",
                    subtitle = "subtitle",
                ),
                Event(
                    id = "1",
                    title = "Event 1",
                    date = Date("2022-01-01", "12:00", DateType.STANDARD),
                    imageUrl = "https://via.placeholder.com/150",
                    videoUrl = "https://www.youtube.com/watch?v=123",
                    subtitle = "subtitle",
                )
            )
        ),
        onEventClick = {},
        onRetryEvents = {}
    )
}

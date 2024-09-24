package pl.adrianczerwinski.dazn.main.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.adrianczerwinski.dazn.events.ui.EventsScreen
import pl.adrianczerwinski.dazn.events.ui.model.EventsUiState
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.EVENTS
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.SCHEDULE

@Composable
internal fun AppNavigation(
    navHostController: NavHostController,
    eventsUiState: EventsUiState,
    onEventClick: (String) -> Unit,
    onRetryEvents: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = EVENTS
    ) {
        composable(EVENTS) {
            EventsScreen(
                state = eventsUiState,
                onEventClick = onEventClick,
                onRetryEvents = onRetryEvents
            )
        }
        composable(SCHEDULE) {}
    }
}

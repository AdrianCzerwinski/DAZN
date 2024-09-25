package pl.adrianczerwinski.dazn.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import pl.adrianczerwinski.dazn.common.theme.DAZNTheme
import pl.adrianczerwinski.dazn.common.views.BottomNavBar
import pl.adrianczerwinski.dazn.events.ui.EventsViewModel
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.EVENTS
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.SCHEDULE
import pl.adrianczerwinski.dazn.main.nav.AppNavigation
import pl.adrianczerwinski.dazn.player.PlayerActivity
import pl.adrianczerwinski.dazn.schedule.ui.ScheduleViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val bottomNavOptions = navOptions {
        popUpTo(EVENTS) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }

    private val eventsViewModel: EventsViewModel by viewModels()
    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setScreen()
    }

    private fun openEvent(url: String) {
        PlayerActivity.startActivity(this, url)
    }

    private fun setScreen() {
        setContent {
            DAZNTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(
                                currentRoute = currentDestination?.route ?: EVENTS,
                                onEventsClick = { navController.navigate(EVENTS, bottomNavOptions) },
                                onScheduleClick = { navController.navigate(SCHEDULE, bottomNavOptions) }
                            )
                        }
                    ) { innerPadding ->
                        AppNavigation(
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController,
                            eventsUiState = eventsViewModel.state.collectAsStateWithLifecycle().value,
                            scheduleUiState = scheduleViewModel.state.collectAsStateWithLifecycle().value,
                            onEventClick = { url -> openEvent(url) },
                            onRetryEvents = { eventsViewModel.getEvents() },
                            onRetrySchedule = { scheduleViewModel.getSchedule() }
                        )
                    }
                }
            }
        }
    }
}
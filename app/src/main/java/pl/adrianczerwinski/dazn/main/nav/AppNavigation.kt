package pl.adrianczerwinski.dazn.main.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.EVENTS
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.SCHEDULE

@Composable
internal fun AppNavigation(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = EVENTS
    ) {
        composable(EVENTS) {}
        composable(SCHEDULE) {}
    }
}

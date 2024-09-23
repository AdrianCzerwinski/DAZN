package pl.adrianczerwinski.dazn.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import pl.adrianczerwinski.dazn.common.theme.DAZNTheme
import pl.adrianczerwinski.dazn.common.views.BottomNavBar
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.EVENTS
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.SCHEDULE
import pl.adrianczerwinski.dazn.main.nav.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val bottomNavOptions = navOptions {
        popUpTo(EVENTS) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                    ) {
                        AppNavigation(modifier = Modifier.padding(it), navHostController = navController)
                    }
                }
            }
        }
    }
}
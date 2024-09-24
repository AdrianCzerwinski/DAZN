package pl.adrianczerwinski.dazn.common.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.EVENTS
import pl.adrianczerwinski.dazn.main.nav.BottomNavDestinations.SCHEDULE

@Composable
internal fun BottomNavBar(
    currentRoute: String,
    onEventsClick: () -> Unit = {},
    onScheduleClick: () -> Unit = {}
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 2.dp
    ) {
        BottomNavItem(
            isOnTop = currentRoute == EVENTS,
            onClick = onEventsClick,
            content = {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Events Icon",
                )
            },
        )
        BottomNavItem(
            isOnTop = currentRoute == SCHEDULE,
            onClick = onScheduleClick,
            content = {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Schedule Icon"
                )
            },
        )
    }
}

@Composable
private fun RowScope.BottomNavItem(
    isOnTop: Boolean,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .weight(1f)
        .clickable { onClick() },
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    content()

    if (isOnTop) HorizontalDivider(
        modifier = Modifier.width(32.dp),
        thickness = 3.dp
    )
}

@LightDarkPreview
@Composable
private fun BottomNavBarPreview() = ColumnPreview {
    BottomNavBar(currentRoute = EVENTS)
}

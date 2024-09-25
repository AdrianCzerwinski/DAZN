package pl.adrianczerwinski.dazn.schedule.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.adrianczerwinski.dazn.common.models.Date
import pl.adrianczerwinski.dazn.common.models.DateType
import pl.adrianczerwinski.dazn.common.views.ColumnPreview
import pl.adrianczerwinski.dazn.common.views.LightDarkPreview
import pl.adrianczerwinski.dazn.common.views.helpers.getDateText
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent

const val SCHEDULE_EVENT_ITEM_HEIGHT = 120

@Composable
internal fun ScheduleEventItem(
    event: ScheduledEvent
) = Row(
    modifier = Modifier.fillMaxWidth().height(SCHEDULE_EVENT_ITEM_HEIGHT.dp),
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(event.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(SCHEDULE_EVENT_ITEM_HEIGHT.dp)
    )

    EventInfo(event)
}

@Composable
private fun RowScope.EventInfo(
    event: ScheduledEvent
) = Column(
    modifier = Modifier.weight(3f).fillMaxHeight(),
    verticalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = event.title,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        text = event.subtitle,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.weight(1f))
    Text(
        text = getDateText(event.date),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@LightDarkPreview
@Composable
private fun ScheduleEventItemPreview() = ColumnPreview {
    ScheduleEventItem(
        event = ScheduledEvent(
            id = "id",
            imageUrl = "https://via.placeholder.com/150",
            title = "Event Title",
            subtitle = "Event Subtitle",
            date = Date(
                date = "2022-12-31",
                time = "23:59",
                type = DateType.STANDARD
            )
        )
    )
}

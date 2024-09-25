package pl.adrianczerwinski.dazn.events.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.adrianczerwinski.dazn.common.views.GradientOverlay
import pl.adrianczerwinski.dazn.common.views.helpers.getDateText
import pl.adrianczerwinski.dazn.events.domain.model.Event

@Composable
internal fun BoxScope.ImageOverlay(
    isImageLoaded: Boolean,
    maxHeight: Dp,
    event: Event
) {
    if (isImageLoaded) {
        GradientOverlay(maxHeight.value * 2)

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(text = event.title, style = MaterialTheme.typography.labelMedium, color = Color.White)
            Text(text = event.subtitle, style = MaterialTheme.typography.labelSmall, color = Color.White)
        }

        Text(
            text = getDateText(date = event.date),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        )
    }
}

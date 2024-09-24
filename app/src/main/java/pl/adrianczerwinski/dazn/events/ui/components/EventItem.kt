package pl.adrianczerwinski.dazn.events.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Success
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import pl.adrianczerwinski.dazn.events.domain.model.Event

private const val ASPECT_RATIO = 1.25f

@Composable
internal fun EventItem(
    event: Event,
    onEventClick: (String) -> Unit
) = Card(
    modifier = Modifier
        .padding(12.dp)
        .clickable { onEventClick(event.videoUrl) }
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ASPECT_RATIO)
    ) {
        var isImageLoaded by remember {
            mutableStateOf(false)
        }

        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ASPECT_RATIO),
            model = event.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        ) {
            if (painter.state is Success) {
                isImageLoaded = true
                SubcomposeAsyncImageContent()
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(60.dp))
                }
            }
        }
        ImageOverlay(isImageLoaded = isImageLoaded, maxHeight = maxHeight, event = event)
    }
}
package pl.adrianczerwinski.dazn.player.ui.components

import androidx.annotation.OptIn
import androidx.compose.foundation.AndroidExternalSurface
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

@OptIn(UnstableApi::class)
@Composable
fun VideoSurface(
    exoPlayer: ExoPlayer,
    modifier: Modifier = Modifier
) {
    val videoFormat = exoPlayer.videoFormat
    val aspectRatio = if (videoFormat != null) {
        videoFormat.pixelWidthHeightRatio * videoFormat.width / videoFormat.height
    } else {
        16f / 9f
    }

    AndroidExternalSurface(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(aspectRatio),
        onInit = {
            onSurface { surface, _, _ ->
                exoPlayer.setVideoSurface(surface)
            }
        },
    )
}
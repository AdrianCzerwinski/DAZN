package pl.adrianczerwinski.dazn.player.ui

import androidx.annotation.DrawableRes
import androidx.annotation.OptIn
import androidx.compose.foundation.AndroidExternalSurface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import pl.adrianczerwinski.dazn.R
import pl.adrianczerwinski.dazn.player.ui.model.PlayerUiState

@Composable
fun PlayerScreen(
    state: PlayerUiState,
    exoPlayer: ExoPlayer,
    modifier: Modifier = Modifier,
    togglePlay: () -> Unit,
    onPlayerScreenClick: () -> Unit
) {
    LaunchedEffect(true) {
        exoPlayer.seekTo(state.position)
    }

    Box(modifier = modifier.background(Color.Black)) {
        VideoSurface(
            modifier = Modifier.clickable { onPlayerScreenClick() },
            exoPlayer = exoPlayer,
        )

        VideoControls(
            modifier = Modifier.align(Alignment.Center),
            imageId = if (state.isPlaying) {
                R.drawable.ic_play
            } else {
                R.drawable.ic_pause
            },
            isPlayButtonVisible = state.isPlayButtonVisible,
            onClick = { togglePlay() }
        )
    }
}

@Composable
private fun VideoControls(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    isPlayButtonVisible: Boolean,
    onClick: () -> Unit,
) {
    if (isPlayButtonVisible) {
        IconButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                painter = painterResource(imageId),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun VideoSurface(
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
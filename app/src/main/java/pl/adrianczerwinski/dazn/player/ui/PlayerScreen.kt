package pl.adrianczerwinski.dazn.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.exoplayer.ExoPlayer
import pl.adrianczerwinski.dazn.R
import pl.adrianczerwinski.dazn.player.ui.components.VideoControls
import pl.adrianczerwinski.dazn.player.ui.components.VideoSurface
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

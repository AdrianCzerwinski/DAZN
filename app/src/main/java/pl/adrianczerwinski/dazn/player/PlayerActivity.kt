package pl.adrianczerwinski.dazn.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import pl.adrianczerwinski.dazn.player.ui.PlayerScreen
import pl.adrianczerwinski.dazn.player.ui.PlayerViewModel

@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {

    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (!::player.isInitialized) {
            player = ExoPlayer.Builder(this).build().apply {
                playWhenReady = true
                setMediaItem(MediaItem.fromUri(viewModel.url))
            }
        }

        setScreen()
    }

    override fun onStop() {
        super.onStop()
        viewModel.savePlayerPosition(player.currentPosition)
        player.stop()
    }

    override fun onStart() {
        super.onStart()
        player.prepare()
    }

    private fun setScreen() {
        setContent {
            PlayerScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                exoPlayer = player,
                modifier = Modifier.fillMaxWidth(),
                togglePlay = { togglePlay() },
                onPlayerScreenClick = { viewModel.onPlayerScreenClick() }
            )
        }
    }

    private fun togglePlay() {
        viewModel.togglePlay()

        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
    }

    companion object {
        const val URL_ARG = "url"

        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(URL_ARG, url)
            }
            context.startActivity(intent)
        }
    }
}
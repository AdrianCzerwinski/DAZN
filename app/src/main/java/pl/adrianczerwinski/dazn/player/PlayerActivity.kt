package pl.adrianczerwinski.dazn.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pl.adrianczerwinski.dazn.player.ui.PlayerViewModel

@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {

    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setScreen()
    }

    private fun setScreen() {
        setContent {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = viewModel.url)
            }
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
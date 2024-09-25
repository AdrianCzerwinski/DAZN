package pl.adrianczerwinski.dazn.player.ui.model

data class PlayerUiState(
    val position: Long = 0L,
    val isPlaying: Boolean = false,
    val isPlayButtonVisible: Boolean = false
)
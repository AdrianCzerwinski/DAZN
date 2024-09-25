package pl.adrianczerwinski.dazn.player.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.adrianczerwinski.dazn.player.PlayerActivity.Companion.URL_ARG
import pl.adrianczerwinski.dazn.player.ui.model.PlayerUiState
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val url: String = savedStateHandle[URL_ARG] ?: ""

    private val _state = MutableStateFlow(PlayerUiState())
    val state: StateFlow<PlayerUiState> = _state.asStateFlow()

    fun savePlayerPosition(position: Long) {
        _state.value = _state.value.copy(position = position)
    }

    fun togglePlay() = viewModelScope.launch {
        _state.value = _state.value.copy(isPlaying = !_state.value.isPlaying)
        delay(2000L)
        onPlayerScreenClick()
    }

    fun onPlayerScreenClick() {
        _state.value = _state.value.copy(isPlayButtonVisible = !_state.value.isPlayButtonVisible)
    }
}


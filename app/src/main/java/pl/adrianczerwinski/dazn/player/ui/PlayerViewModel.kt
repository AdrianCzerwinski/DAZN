package pl.adrianczerwinski.dazn.player.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.adrianczerwinski.dazn.player.PlayerActivity.Companion.URL_ARG
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val url: String = savedStateHandle[URL_ARG] ?: ""
}
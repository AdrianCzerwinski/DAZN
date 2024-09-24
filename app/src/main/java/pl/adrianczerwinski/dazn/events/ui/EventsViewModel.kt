package pl.adrianczerwinski.dazn.events.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.adrianczerwinski.dazn.events.domain.usecase.GetEventsUseCase
import pl.adrianczerwinski.dazn.events.ui.model.EventsUiState
import pl.adrianczerwinski.dazn.events.ui.model.ScreenState
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EventsUiState())
    val state = _state
        .onStart { getEvents() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = EventsUiState()
        )

    fun getEvents() = viewModelScope.launch {
        _state.value = _state.value.copy(screenState = ScreenState.LOADING)

        getEventsUseCase.invoke()?.let { events ->
            if (events.isNotEmpty()) {
                _state.value = _state.value.copy(screenState = ScreenState.CONTENT, events = events)
            } else {
                _state.value = _state.value.copy(screenState = ScreenState.EMPTY)
            }
        } ?: run {
            _state.value = _state.value.copy(screenState = ScreenState.ERROR)
        }
    }
}

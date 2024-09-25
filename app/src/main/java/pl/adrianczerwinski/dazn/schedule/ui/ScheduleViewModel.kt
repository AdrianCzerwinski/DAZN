package pl.adrianczerwinski.dazn.schedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.adrianczerwinski.dazn.common.models.ScreenState
import pl.adrianczerwinski.dazn.schedule.domain.usecase.GetScheduledEventsUseCase
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduledEventsUseCase: GetScheduledEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleUiState())
    val state = _state.asStateFlow()

    init {
        getSchedule()
    }

    fun getSchedule() = viewModelScope.launch {
        _state.value = _state.value.copy(screenState = ScreenState.LOADING)

        getScheduledEventsUseCase.invoke()?.let { events ->
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

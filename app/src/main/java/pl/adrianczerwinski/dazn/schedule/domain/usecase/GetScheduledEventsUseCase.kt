package pl.adrianczerwinski.dazn.schedule.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import pl.adrianczerwinski.dazn.schedule.data.repository.ScheduleRepository
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetScheduledEventsUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(): Flow<List<ScheduledEvent>?> = scheduleRepository.getSchedule().mapLatest {
        it.getOrNull()
    }
}

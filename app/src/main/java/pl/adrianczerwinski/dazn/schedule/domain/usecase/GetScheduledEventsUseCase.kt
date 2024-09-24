package pl.adrianczerwinski.dazn.schedule.domain.usecase

import pl.adrianczerwinski.dazn.schedule.data.repository.ScheduleRepository
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import javax.inject.Inject

class GetScheduledEventsUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(): List<ScheduledEvent>? = scheduleRepository.getSchedule().getOrNull()
}

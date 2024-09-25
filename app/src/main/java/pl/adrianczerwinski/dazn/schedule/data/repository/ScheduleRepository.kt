package pl.adrianczerwinski.dazn.schedule.data.repository

import kotlinx.coroutines.flow.Flow
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent

interface ScheduleRepository {
    fun getSchedule(): Flow<Result<List<ScheduledEvent>>>
}

package pl.adrianczerwinski.dazn.schedule.data.repository

import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent

interface ScheduleRepository {
    suspend fun getSchedule(): Result<List<ScheduledEvent>>
}

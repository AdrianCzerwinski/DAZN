package pl.adrianczerwinski.dazn.schedule.data.repository

import android.util.Log
import pl.adrianczerwinski.dazn.schedule.data.mapper.ScheduledEventsMapper
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import pl.adrianczerwinski.dazn.schedule.network.ScheduleClient
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val restClient: ScheduleClient,
    private val mapper: ScheduledEventsMapper
) : ScheduleRepository {

    override suspend fun getSchedule(): Result<List<ScheduledEvent>> = runCatching {
        restClient.getSchedule()
    }.fold(
        onSuccess = { events -> Result.success(mapper.mapToUpcomingEvents(events)) },
        onFailure = { error ->
            Log.d("ScheduleRepositoryImpl", "getSchedule: $error")
            Result.failure(error)
        }
    )
}

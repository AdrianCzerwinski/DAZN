package pl.adrianczerwinski.dazn.schedule.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.adrianczerwinski.dazn.schedule.data.mapper.ScheduledEventsMapper
import pl.adrianczerwinski.dazn.schedule.domain.model.ScheduledEvent
import pl.adrianczerwinski.dazn.schedule.network.ScheduleClient
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val restClient: ScheduleClient,
    private val mapper: ScheduledEventsMapper
) : ScheduleRepository {

    override fun getSchedule(): Flow<Result<List<ScheduledEvent>>> = flow {
        while (true) {
            val result = runCatching {
                restClient.getSchedule()
            }.fold(
                onSuccess = { events -> Result.success(mapper.mapToUpcomingEvents(events)) },
                onFailure = { error -> Result.failure(error) }
            )
            emit(result)
            delay(30000)
        }
    }
}

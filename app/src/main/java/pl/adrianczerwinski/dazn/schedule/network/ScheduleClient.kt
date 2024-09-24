package pl.adrianczerwinski.dazn.schedule.network

import pl.adrianczerwinski.dazn.schedule.data.model.ScheduledEventResponse
import retrofit2.http.GET

interface ScheduleClient {

    @GET("/getSchedule")
    suspend fun getSchedule(): List<ScheduledEventResponse>
}
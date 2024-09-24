package pl.adrianczerwinski.dazn.events.network

import pl.adrianczerwinski.dazn.events.data.model.EventResponse
import retrofit2.http.GET

interface EventsClient {

    @GET("/getEvents")
    suspend fun getEvents(): List<EventResponse>
}

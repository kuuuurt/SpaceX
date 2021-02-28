package com.kuuuurt.spacex.shared.data

import com.kuuuurt.spacex.shared.data.mappers.toDomain
import com.kuuuurt.spacex.shared.data.remote.models.RemoteHistoricalEvent
import com.kuuuurt.spacex.shared.data.remote.models.RemoteLaunch
import com.kuuuurt.spacex.shared.domain.entities.HistoricalEvent
import com.kuuuurt.spacex.shared.domain.repositories.HistoricalEventsRepository
import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository
import io.ktor.client.*
import io.ktor.client.request.*

class RealHistoricalEventsRepository(
    private val httpClient: HttpClient
) : HistoricalEventsRepository {

    override suspend fun getHistoricalEvents() = httpClient
        .get<List<RemoteHistoricalEvent>>("https://api.spacexdata.com/v4/history")
        .map { it.toDomain() }
}

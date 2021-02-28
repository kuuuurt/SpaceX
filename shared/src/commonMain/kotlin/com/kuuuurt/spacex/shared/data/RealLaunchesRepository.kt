package com.kuuuurt.spacex.shared.data

import com.kuuuurt.spacex.shared.data.mappers.toDomain
import com.kuuuurt.spacex.shared.data.remote.models.RemoteLaunch
import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository
import io.ktor.client.*
import io.ktor.client.request.*

class RealLaunchesRepository(private val httpClient: HttpClient) : LaunchesRepository {
    override suspend fun getAllLaunches() = httpClient
        .get<List<RemoteLaunch>>("https://api.spacexdata.com/v4/launches")
        .map { it.toDomain() }
}

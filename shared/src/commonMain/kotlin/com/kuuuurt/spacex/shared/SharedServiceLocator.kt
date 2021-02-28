package com.kuuuurt.spacex.shared

import com.kuuuurt.spacex.shared.data.RealHistoricalEventsRepository
import com.kuuuurt.spacex.shared.data.RealLaunchesRepository
import com.kuuuurt.spacex.shared.domain.repositories.HistoricalEventsRepository
import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository
import com.kuuuurt.spacex.shared.domain.usecases.GetHistoricalEvents
import com.kuuuurt.spacex.shared.domain.usecases.GetLaunches
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object SharedServiceLocator {
    // Data
    private val jsonSerializerConfig = Json {
        isLenient = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        encodeDefaults = false
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(jsonSerializerConfig)
            accept(ContentType.Application.Json)
        }
    }

    val launchesRepository: LaunchesRepository = RealLaunchesRepository(httpClient)
    val historicalEventsRepository: HistoricalEventsRepository = RealHistoricalEventsRepository(
        httpClient
    )


    // Domain
    val getLaunches get() = GetLaunches(launchesRepository)
    val getHistoricalEvents get() = GetHistoricalEvents(historicalEventsRepository)
}
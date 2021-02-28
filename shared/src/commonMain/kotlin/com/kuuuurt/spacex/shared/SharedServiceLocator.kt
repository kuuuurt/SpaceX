package com.kuuuurt.spacex.shared

import com.kuuuurt.spacex.shared.data.RealLaunchesRepository
import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository
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

    val launchesRepository: LaunchesRepository = RealLaunchesRepository(
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(jsonSerializerConfig)
                accept(ContentType.Application.Json)
            }
        }
    )

    // Domain
    val getLaunches get() = GetLaunches(launchesRepository)
}
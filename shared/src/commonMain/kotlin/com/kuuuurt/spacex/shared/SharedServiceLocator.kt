package com.kuuuurt.spacex.shared

import com.kuuuurt.spacex.shared.data.RealLaunchesRepository
import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository
import com.kuuuurt.spacex.shared.domain.usecases.GetLaunches
import io.ktor.client.*

object SharedServiceLocator {
    // Data
    val launchesRepository: LaunchesRepository = RealLaunchesRepository(HttpClient())

    // Domain
    val getLaunches get() = GetLaunches(launchesRepository)
}
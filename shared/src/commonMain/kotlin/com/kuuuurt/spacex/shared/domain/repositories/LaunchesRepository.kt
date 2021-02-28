package com.kuuuurt.spacex.shared.domain.repositories

import com.kuuuurt.spacex.shared.domain.entities.Launch

interface LaunchesRepository {
    suspend fun getAllLaunches(): List<Launch>
}
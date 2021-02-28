package com.kuuuurt.spacex.shared.domain.repositories

import com.kuuuurt.spacex.shared.domain.entities.Launch

interface LaunchRepository {
    suspend fun getAllLaunches(): List<Launch>
}
package com.kuuuurt.spacex.shared.domain.usecases

import com.kuuuurt.spacex.shared.domain.repositories.LaunchesRepository

class GetLaunches(private val launchesRepository: LaunchesRepository) {
    suspend operator fun invoke() = launchesRepository.getAllLaunches()
        .sortedByDescending { it.date }
}
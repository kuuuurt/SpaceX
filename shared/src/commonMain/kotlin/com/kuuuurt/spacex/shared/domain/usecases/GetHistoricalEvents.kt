package com.kuuuurt.spacex.shared.domain.usecases

import com.kuuuurt.spacex.shared.domain.repositories.HistoricalEventsRepository

class GetHistoricalEvents(private val historicalEventsRepository: HistoricalEventsRepository) {
    suspend operator fun invoke() = historicalEventsRepository.getHistoricalEvents()
        .sortedByDescending { it.date }
}
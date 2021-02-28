package com.kuuuurt.spacex.shared.domain.repositories

import com.kuuuurt.spacex.shared.domain.entities.HistoricalEvent

interface HistoricalEventsRepository {
    suspend fun getHistoricalEvents(): List<HistoricalEvent>
}
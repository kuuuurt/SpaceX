package com.kuuuurt.spacex.shared.data.mappers

import com.kuuuurt.spacex.shared.data.remote.models.RemoteHistoricalEvent
import com.kuuuurt.spacex.shared.domain.entities.HistoricalEvent

fun RemoteHistoricalEvent.toDomain() = HistoricalEvent(
    title = title,
    date = eventDateUnix * 1000,
    details = details
)
package com.kuuuurt.spacex.shared.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteHistoricalEvent(
    val title: String,
    @SerialName("event_date_unix")
    val eventDateUnix: Long,
    val details: String
)
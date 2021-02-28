package com.kuuuurt.spacex.compose.domain.entities

data class HistoricalEvent(
    val title: String,
    val date: Long,
    val details: String
)
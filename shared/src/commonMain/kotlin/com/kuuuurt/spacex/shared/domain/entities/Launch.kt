package com.kuuuurt.spacex.shared.domain.entities

data class Launch(
    val name: String,
    val flightNumber: String,
    val date: Long,
    val patch: String,
    val images: List<String>
)
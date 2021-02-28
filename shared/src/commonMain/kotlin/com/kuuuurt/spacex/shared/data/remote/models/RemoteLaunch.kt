package com.kuuuurt.spacex.shared.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLaunch(
    val name: String,
    @SerialName("flight_number")
    val flightNumber: String,
    @SerialName("date_unit")
    val date: Long,
    val links: RemoteLaunchLinks
)

@Serializable
data class RemoteLaunchLinks(
    val patch: RemoteLaunchLinksPatch,
    val flickr: RemoteLaunchLinksFlickr
)

@Serializable
data class RemoteLaunchLinksPatch(
    val small: String
)

@Serializable
data class RemoteLaunchLinksFlickr(
    val original: List<String>
)
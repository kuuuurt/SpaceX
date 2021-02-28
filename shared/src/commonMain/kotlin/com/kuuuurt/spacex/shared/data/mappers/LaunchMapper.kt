package com.kuuuurt.spacex.shared.data.mappers

import com.kuuuurt.spacex.shared.data.remote.models.RemoteLaunch
import com.kuuuurt.spacex.shared.domain.entities.Launch

fun RemoteLaunch.toDomain() = Launch(
    name = name,
    flightNumber = flightNumber,
    date = date * 1000,
    patch = links.patch.small,
    images = links.flickr.original
)
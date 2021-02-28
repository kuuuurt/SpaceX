package com.kuuuurt.spacex.compose.presentation.helpers

import android.annotation.SuppressLint
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("NewApi")
fun Long.toStringDate(pattern: String = "MM/dd/yyyy HH:mm:ss", timeZone: TimeZone? = null): String {
    return DateTimeFormatter.ofPattern(pattern).format(
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(this),
            timeZone?.toZoneId() ?: TimeZone.getDefault().toZoneId()
        )
    )
}
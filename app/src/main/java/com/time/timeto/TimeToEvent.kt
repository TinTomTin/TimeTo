package com.time.timeto

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


data class TimeToEvent(
    val name: String,
    val format: String,
    val eventDate: LocalDateTime,
    val createdDate: LocalDateTime
) {

    fun timeUntil(): Long {
        return ChronoUnit.HOURS.between(LocalDateTime.now(), eventDate)
    }
}
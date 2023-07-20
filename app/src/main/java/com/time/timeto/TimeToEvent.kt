package com.time.timeto

import kotlinx.serialization.Contextual
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlinx.serialization.Serializable

@Serializable
data class TimeToEvent(
    val name: String,
    val format: String,
    @Contextual
    val eventDate: LocalDateTime,
    @Contextual
    val createdDate: LocalDateTime
) {

    fun timeUntil(): Long {
        return ChronoUnit.HOURS.between(LocalDateTime.now(), eventDate)
    }
}
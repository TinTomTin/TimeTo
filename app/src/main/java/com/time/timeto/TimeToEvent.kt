package com.time.timeto

import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class TimeToEvent(
    val name: String,
    val format: String,
    @Serializable(DateTimeSerializer::class)
    val eventDate: LocalDateTime,
    @Serializable(DateTimeSerializer::class)
    val createdDate: LocalDateTime
) {

    fun timeUntil(): Long {
        return ChronoUnit.HOURS.between(LocalDateTime.now(), eventDate)
    }
}

object DateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    //TODO: specify datetime format explicitly
    override fun serialize(encoder: Encoder, dtValue: LocalDateTime) = encoder.encodeString(dtValue.toString())
    override fun deserialize(decoder: Decoder): LocalDateTime {
        val input = decoder.decodeString()
        return LocalDateTime.parse(input)
    }
}
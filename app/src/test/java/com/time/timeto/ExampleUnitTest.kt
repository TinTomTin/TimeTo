package com.time.timeto

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun serialize_event(){
        val eventDate = LocalDateTime.of(2023,6,8,13,0)
        val createdDate = LocalDateTime.of(2023,5,18,22,19)
        val testEvent = TimeToEvent("Test Event", "HH", eventDate, createdDate)
        val serialized = Json.encodeToString(TimeToEvent.serializer(), testEvent)
        assertEquals("{\"name\":\"Test Event\",\"format\":\"HH\",\"eventDate\":\"2023-06-08T13:00\",\"createdDate\":\"2023-05-18T22:19\"}", serialized)
    }

    @Test
    fun serialize_event_list(){
        val eventDate = LocalDateTime.of(2023,6,8,13,0)
        val createdDate = LocalDateTime.of(2023,5,18,22,19)
        val testEventOne = TimeToEvent("One", "HH", eventDate, createdDate)
        val testEventTwo = TimeToEvent("Two", "DD", eventDate, createdDate)
        val events : List<TimeToEvent> = listOf(testEventOne, testEventTwo)
        val lstSerializer = ListSerializer(TimeToEvent.serializer())
        val expected = "[{\"name\":\"One\",\"format\":\"HH\",\"eventDate\":\"2023-06-08T13:00\",\"createdDate\":\"2023-05-18T22:19\"},{\"name\":\"Two\",\"format\":\"DD\",\"eventDate\":\"2023-06-08T13:00\",\"createdDate\":\"2023-05-18T22:19\"}]"
        assertEquals(expected, Json.encodeToString(lstSerializer, events))
    }

    @Test
    fun deserialize_event(){
        val eventString = "{\"name\":\"Test Event\",\"format\":\"HH\",\"eventDate\":\"2023-06-08T13:00\",\"createdDate\":\"2023-05-18T22:19\"}"
        val event = Json.decodeFromString(TimeToEvent.serializer(),eventString)
        val eventDate = LocalDateTime.of(2023,6,8,13,0)
        val createdDate = LocalDateTime.of(2023,5,18,22,19)
        val expectedEvent = TimeToEvent("Test Event", "HH", eventDate, createdDate)
        assertEquals(expectedEvent, event)
    }
}
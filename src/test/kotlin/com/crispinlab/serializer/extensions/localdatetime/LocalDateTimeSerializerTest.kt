package com.crispinlab.serializer.extensions.localdatetime

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LocalDateTimeSerializerTest {
    @Test
    fun localDateTimeSerializationTest() {
        // given
        val localDateTime: LocalDateTime = LocalDateTime.now()

        // when
        val result: String = Json.encodeToString(LocalDateTimeSerializer, localDateTime)

        // then
        Assertions.assertThat(result).isEqualTo("\"${localDateTime}\"")
    }

    @Test
    fun localDateTimeDeserializationTest() {
        // given
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val jsonString = "\"${localDateTime}\""

        // when
        val result: LocalDateTime = Json.decodeFromString(LocalDateTimeSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(localDateTime)
    }

    @Test
    fun serializationDeserializationRoundTripTest() {
        // given
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val serializedString: String = Json.encodeToString(LocalDateTimeSerializer, localDateTime)

        // when
        val deserializedLocalDateTime: LocalDateTime =
            Json.decodeFromString(LocalDateTimeSerializer, serializedString)

        // then
        Assertions
            .assertThat(deserializedLocalDateTime)
            .isEqualTo(localDateTime)
            .isInstanceOf(LocalDateTime::class.java)
    }

    @Test
    fun specificLocalDateTimeSerializationTest() {
        // given
        val localDateTime: LocalDateTime =
            LocalDateTime.parse("2025-01-01T11:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        // when
        val result: String = Json.encodeToString(LocalDateTimeSerializer, localDateTime)

        // then
        Assertions.assertThat(result).isEqualTo("\"${localDateTime}\"")
    }

    @Test
    fun specificLocalDateTimeDeserializationTest() {
        // given
        val localDateTime: LocalDateTime =
            LocalDateTime.parse("2025-01-01T11:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val jsonString = "\"${localDateTime}\""

        // when
        val result: LocalDateTime = Json.decodeFromString(LocalDateTimeSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(localDateTime)
    }
}

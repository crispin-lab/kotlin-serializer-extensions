package com.crispinlab.serializer.extensions.instant

import java.time.Instant
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class InstantSerializerTest {
    @Test
    fun instantSerializationTest() {
        // given
        val instant: Instant = Instant.now()

        // when
        val result: String = Json.encodeToString(InstantSerializer, instant)

        // then
        Assertions.assertThat(result).isEqualTo("\"${instant}\"")
    }

    @Test
    fun instantDeserializationTest() {
        // given
        val instant: Instant = Instant.now()
        val jsonString = "\"${instant}\""

        // when
        val result: Instant = Json.decodeFromString(InstantSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(instant)
    }

    @Test
    fun serializationDeserializationRoundTripTest() {
        // given
        val instant: Instant = Instant.now()
        val serializedString: String = Json.encodeToString(InstantSerializer, instant)

        // when
        val deserializedInstant: Instant =
            Json.decodeFromString(InstantSerializer, serializedString)

        // then
        Assertions
            .assertThat(deserializedInstant)
            .isEqualTo(instant)
            .isInstanceOf(Instant::class.java)
    }

    @Test
    fun specificInstantSerializationTest() {
        // given
        val instant: Instant = Instant.parse("2025-05-31T15:00:00Z")

        // when
        val result: String = Json.encodeToString(InstantSerializer, instant)

        // then
        Assertions.assertThat(result).isEqualTo("\"${instant}\"")
    }

    @Test
    fun specificInstantDeserializationTest() {
        // give
        val instant: Instant = Instant.parse("2025-05-31T15:00:00Z")
        val jsonString = "\"${instant}\""

        // when
        val result: Instant = Json.decodeFromString(InstantSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(instant)
    }
}

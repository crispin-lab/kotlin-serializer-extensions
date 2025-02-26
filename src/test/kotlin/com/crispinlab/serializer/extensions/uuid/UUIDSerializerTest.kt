package com.crispinlab.serializer.extensions.uuid

import java.util.UUID
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UUIDSerializerTest {
    @Test
    fun uuidSerializationTest() {
        // given
        val uuid: UUID = UUID.randomUUID()

        // when
        val result: String = Json.encodeToString(UUIDSerializer, uuid)

        // then
        Assertions.assertThat(result).isEqualTo("\"${uuid}\"")
    }

    @Test
    fun uuidDeserializationTest() {
        // given
        val uuid: UUID = UUID.randomUUID()
        val jsonString = "\"${uuid}\""

        // when
        val result: UUID = Json.decodeFromString(UUIDSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(uuid)
    }

    @Test
    fun serializationDeserializationRoundTripTest() {
        // given
        val uuid: UUID = UUID.randomUUID()
        val serializedString: String = Json.encodeToString(UUIDSerializer, uuid)

        // when
        val deserializedUUID: UUID = Json.decodeFromString(UUIDSerializer, serializedString)

        // then
        Assertions
            .assertThat(deserializedUUID)
            .isEqualTo(uuid)
            .isExactlyInstanceOf(UUID::class.java)
    }

    @Test
    fun specificUUIDSerializationTest() {
        // given
        val uuid: UUID = UUID.fromString("f81d4fae-7dec-11d0-a765-00a0c91e6bf6")

        // when
        val result: String = Json.encodeToString(UUIDSerializer, uuid)

        // then
        Assertions.assertThat(result).isEqualTo("\"${uuid}\"")
    }

    @Test
    fun specificUUIDDeserializationTest() {
        // given
        val uuid: UUID = UUID.fromString("f81d4fae-7dec-11d0-a765-00a0c91e6bf6")
        val jsonString = "\"${uuid}\""

        // when
        val result: UUID = Json.decodeFromString(UUIDSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(uuid)
    }
}

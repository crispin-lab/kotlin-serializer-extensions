package com.crispinlab.serializer.extensions.bigdecimal

import java.math.BigDecimal
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BigDecimalSerializerTest {
    @Test
    fun bigDecimalSerializationTest() {
        // given
        val bigDecimal: BigDecimal = BigDecimal.valueOf(1000L)

        // when
        val result: String = Json.encodeToString(BigDecimalSerializer, bigDecimal)

        // then
        Assertions.assertThat(result).isEqualTo("\"${bigDecimal}\"")
    }

    @Test
    fun bigDecimalDeserializationTest() {
        // given
        val bigDecimal: BigDecimal = BigDecimal.valueOf(1000L)
        val jsonString = "\"${bigDecimal}\""

        // when
        val result: BigDecimal = Json.decodeFromString(BigDecimalSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(bigDecimal)
    }

    @Test
    fun serializationDeserializationRoundTripTest() {
        // given
        val bigDecimal: BigDecimal = BigDecimal.valueOf(1000L)
        val serializedString: String = Json.encodeToString(BigDecimalSerializer, bigDecimal)

        // when
        val deserializedBigDecimal: BigDecimal =
            Json.decodeFromString(BigDecimalSerializer, serializedString)

        // then
        Assertions
            .assertThat(deserializedBigDecimal)
            .isEqualTo(bigDecimal)
            .isInstanceOf(BigDecimal::class.java)
    }

    @Test
    fun specificFloatBigDecimalSerializationTest() {
        // given
        val bigDecimal: BigDecimal = BigDecimal.valueOf(19999.99)

        // when
        val result: String = Json.encodeToString(BigDecimalSerializer, bigDecimal)

        // then
        Assertions.assertThat(result).isEqualTo("\"${bigDecimal}\"")
    }

    @Test
    fun specificFloatBigDecimalDeserializationTest() {
        // given
        val bigDecimal: BigDecimal = BigDecimal.valueOf(19999.99)
        val jsonString = "\"${bigDecimal}\""

        // when
        val result: BigDecimal = Json.decodeFromString(BigDecimalSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(bigDecimal)
    }

    @Test
    fun specificLongBigDecimalSerializationTest() {
        // given
        val bigDecimal =
            BigDecimal("12345678901234567890123456789012345678901234567890")

        // when
        val result: String = Json.encodeToString(BigDecimalSerializer, bigDecimal)

        // then
        Assertions.assertThat(result).isEqualTo("\"${bigDecimal}\"")
    }

    @Test
    fun specificLongBigDecimalDeserializationTest() {
        // given
        val bigDecimal =
            BigDecimal("12345678901234567890123456789012345678901234567890")
        val jsonString = "\"${bigDecimal}\""

        // when
        val result: BigDecimal = Json.decodeFromString(BigDecimalSerializer, jsonString)

        // then
        Assertions.assertThat(result).isEqualTo(bigDecimal)
    }
}

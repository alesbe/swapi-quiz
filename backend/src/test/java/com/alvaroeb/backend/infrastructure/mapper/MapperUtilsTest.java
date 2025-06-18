package com.alvaroeb.backend.infrastructure.mapper;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class MapperUtilsTest {

    @Test
    void mapSwapiDate_shouldParseValidDate() {
        String date = "2023-06-01T12:34:56.123456Z";
        LocalDateTime result = MapperUtils.mapSwapiDate(date);
        assertThat(result).isNotNull();
        assertThat(result.getYear()).isEqualTo(2023);
        assertThat(result.getMonthValue()).isEqualTo(6);
        assertThat(result.getDayOfMonth()).isEqualTo(1);
        assertThat(result.getHour()).isEqualTo(12);
        assertThat(result.getMinute()).isEqualTo(34);
        assertThat(result.getSecond()).isEqualTo(56);
        assertThat(result.getNano()).isEqualTo(123456000);
    }

    @Test
    void mapSwapiDate_shouldReturnNullForNullOrBlank() {
        assertThat(MapperUtils.mapSwapiDate(null)).isNull();
        assertThat(MapperUtils.mapSwapiDate("")).isNull();
        assertThat(MapperUtils.mapSwapiDate("   ")).isNull();
    }

    @Test
    void mapSwapiDate_shouldReturnNullForInvalidFormat() {
        assertThat(MapperUtils.mapSwapiDate("not-a-date")).isNull();
        assertThat(MapperUtils.mapSwapiDate("2023-06-01")).isNull();
    }

    @Test
    void parseIntOrNull_shouldParseValidInteger() {
        assertThat(MapperUtils.parseIntOrNull("123")).isEqualTo(123);
        assertThat(MapperUtils.parseIntOrNull("-456")).isEqualTo(-456);
    }

    @Test
    void parseIntOrNull_shouldReturnNullForInvalidInteger() {
        assertThat(MapperUtils.parseIntOrNull("abc")).isNull();
        assertThat(MapperUtils.parseIntOrNull("123.45")).isNull();
        assertThat(MapperUtils.parseIntOrNull(null)).isNull();
        assertThat(MapperUtils.parseIntOrNull("")).isNull();
    }

    @Test
    void parseLongOrNull_shouldParseValidLong() {
        assertThat(MapperUtils.parseLongOrNull("1234567890123")).isEqualTo(1234567890123L);
        assertThat(MapperUtils.parseLongOrNull("-9876543210")).isEqualTo(-9876543210L);
    }

    @Test
    void parseLongOrNull_shouldReturnNullForInvalidLong() {
        assertThat(MapperUtils.parseLongOrNull("abc")).isNull();
        assertThat(MapperUtils.parseLongOrNull("123.45")).isNull();
        assertThat(MapperUtils.parseLongOrNull(null)).isNull();
        assertThat(MapperUtils.parseLongOrNull("")).isNull();
    }
}
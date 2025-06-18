package com.alvaroeb.backend.infrastructure.mapper;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class MapperUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");

    @Named("mapSwapiDate")
    public static LocalDateTime mapSwapiDate(String date) {
        if (date == null || date.isBlank()) return null;
        try {
            return OffsetDateTime.parse(date, FORMATTER).toLocalDateTime();
        } catch (Exception e) {
            return null;
        }
    }

    @Named("parseIntOrNull")
    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    @Named("parseLongOrNull")
    public static Long parseLongOrNull(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return null;
        }
    }
}

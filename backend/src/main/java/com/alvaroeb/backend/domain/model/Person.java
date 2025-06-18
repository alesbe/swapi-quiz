package com.alvaroeb.backend.domain.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Person {
    private String name;
    private Integer height;
    private Integer mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private Planet homeworld;
    private LocalDateTime created;
    private LocalDateTime edited;
}
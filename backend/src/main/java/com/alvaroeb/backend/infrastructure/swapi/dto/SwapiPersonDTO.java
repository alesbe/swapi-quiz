package com.alvaroeb.backend.infrastructure.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SwapiPersonDTO {
    private String name;
    private String height;
    private String mass;

    @JsonProperty("hair_color")
    private String hairColor;

    @JsonProperty("skin_color")
    private String skinColor;

    @JsonProperty("eye_color")
    private String eyeColor;

    @JsonProperty("birth_year")
    private String birthYear;

    private String gender;
    private String homeworld;
    private String created;
    private String edited;
}
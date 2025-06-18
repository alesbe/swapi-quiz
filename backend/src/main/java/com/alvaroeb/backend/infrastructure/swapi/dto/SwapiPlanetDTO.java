package com.alvaroeb.backend.infrastructure.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SwapiPlanetDTO {
    private String name;
    @JsonProperty("rotation_period")
    private String rotationPeriod;

    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;

    @JsonProperty("surface_water")
    private String surfaceWater;

    private String population;
    private String created;
    private String edited;
}
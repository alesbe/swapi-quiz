package com.alvaroeb.backend.infrastructure.swapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SwapiPlanetsResponse {
    private int count;
    private String next;
    private List<SwapiPlanetDTO> results;
}

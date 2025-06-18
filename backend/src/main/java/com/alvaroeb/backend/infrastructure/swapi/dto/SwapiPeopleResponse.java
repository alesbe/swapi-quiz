package com.alvaroeb.backend.infrastructure.swapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SwapiPeopleResponse {
    private int count;
    private String next;
    private List<SwapiPersonDTO> results;
}
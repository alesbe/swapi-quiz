package com.alvaroeb.backend.domain.port;

import org.springframework.data.domain.Page;

import com.alvaroeb.backend.domain.model.Planet;

public interface PlanetsService {
    Page<Planet> getPlanets(Integer page, Integer size, String search, String sort, String direction);
}
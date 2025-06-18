package com.alvaroeb.backend.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;

@Component
public class PlanetResolver {
    private final SwapiClient swapiClient;
    private final PlanetMapper planetMapper;

    public PlanetResolver(SwapiClient swapiClient, PlanetMapper planetMapper) {
        this.swapiClient = swapiClient;
        this.planetMapper = planetMapper;
    }

    public Planet map(String url) {
        return planetMapper.toPlanet(swapiClient.fetchPlanetFromUrl(url));
    }
}


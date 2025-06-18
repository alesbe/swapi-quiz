package com.alvaroeb.backend.infrastructure.mapper;

import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlanetResolverTest {

    private SwapiClient swapiClient;
    private PlanetMapper planetMapper;
    private PlanetResolver planetResolver;

    @BeforeEach
    void setUp() {
        swapiClient = mock(SwapiClient.class);
        planetMapper = mock(PlanetMapper.class);
        planetResolver = new PlanetResolver(swapiClient, planetMapper);
    }

    @Test
    void map_shouldReturnMappedPlanet() {
        String url = "https://swapi.dev/api/planets/1/";
        SwapiPlanetDTO swapiPlanetResponse = new SwapiPlanetDTO();
        Planet expectedPlanet = new Planet();

        when(swapiClient.fetchPlanetFromUrl(url)).thenReturn(swapiPlanetResponse);
        when(planetMapper.toPlanet(swapiPlanetResponse)).thenReturn(expectedPlanet);

        Planet result = planetResolver.map(url);

        assertThat(result).isSameAs(expectedPlanet);
        verify(swapiClient).fetchPlanetFromUrl(url);
        verify(planetMapper).toPlanet(swapiPlanetResponse);
    }
}
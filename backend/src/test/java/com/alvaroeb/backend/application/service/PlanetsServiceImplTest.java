package com.alvaroeb.backend.application.service;

import com.alvaroeb.backend.application.util.PlanetComparators;
import com.alvaroeb.backend.domain.model.Planet;
import com.alvaroeb.backend.infrastructure.mapper.PlanetMapper;
import com.alvaroeb.backend.infrastructure.swapi.client.SwapiClient;
import com.alvaroeb.backend.infrastructure.swapi.dto.SwapiPlanetDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Page;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlanetsServiceImplTest {

    private SwapiClient swapiClient;
    private PlanetMapper planetMapper;
    private PlanetsServiceImpl planetsService;

    @BeforeEach
    void setUp() {
        swapiClient = mock(SwapiClient.class);
        planetMapper = mock(PlanetMapper.class);
        planetsService = new PlanetsServiceImpl(swapiClient, planetMapper);
    }

    @Test
    void getPlanets_returnsPagedPlanets_sortedAndFiltered() {
        SwapiPlanetDTO dto1 = new SwapiPlanetDTO();
        dto1.setName("Tatooine");
        SwapiPlanetDTO dto2 = new SwapiPlanetDTO();
        dto2.setName("Alderaan");
        SwapiPlanetDTO dto3 = new SwapiPlanetDTO();
        dto3.setName("Hoth");

        Planet planet1 = new Planet();
        planet1.setName("Tatooine");
        Planet planet2 = new Planet();
        planet2.setName("Alderaan");
        Planet planet3 = new Planet();
        planet3.setName("Hoth");

        when(swapiClient.getAllPlanets()).thenReturn(List.of(dto1, dto2, dto3));
        when(planetMapper.toPlanet(dto1)).thenReturn(planet1);
        when(planetMapper.toPlanet(dto2)).thenReturn(planet2);
        when(planetMapper.toPlanet(dto3)).thenReturn(planet3);

        Page<Planet> page = planetsService.getPlanets(0, 2, null, "name", "asc");

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent().get(0).getName()).isEqualTo("Alderaan");
        assertThat(page.getContent().get(1).getName()).isEqualTo("Hoth");
        assertThat(page.getTotalElements()).isEqualTo(3);
    }

    @Test
    void getPlanets_filtersBySearch() {
        SwapiPlanetDTO dto1 = new SwapiPlanetDTO();
        dto1.setName("Tatooine");
        SwapiPlanetDTO dto2 = new SwapiPlanetDTO();
        dto2.setName("Alderaan");

        Planet planet1 = new Planet();
        planet1.setName("Tatooine");
        Planet planet2 = new Planet();
        planet2.setName("Alderaan");

        when(swapiClient.getAllPlanets()).thenReturn(List.of(dto1, dto2));
        when(planetMapper.toPlanet(dto1)).thenReturn(planet1);
        when(planetMapper.toPlanet(dto2)).thenReturn(planet2);

        Page<Planet> page = planetsService.getPlanets(0, 10, "tat", null, null);

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getName()).isEqualTo("Tatooine");
        assertThat(page.getTotalElements()).isEqualTo(1);
    }

    @Test
    void getPlanets_returnsEmptyPage_whenNoResults() {
        when(swapiClient.getAllPlanets()).thenReturn(List.of());
        Page<Planet> page = planetsService.getPlanets(0, 10, null, null, null);
        assertThat(page.getContent()).isEmpty();
        assertThat(page.getTotalElements()).isZero();
    }

    @Test
    void getPlanets_paginationWorks() {
        SwapiPlanetDTO dto1 = new SwapiPlanetDTO();
        dto1.setName("A");
        SwapiPlanetDTO dto2 = new SwapiPlanetDTO();
        dto2.setName("B");
        SwapiPlanetDTO dto3 = new SwapiPlanetDTO();
        dto3.setName("C");

        Planet planet1 = new Planet();
        planet1.setName("A");
        Planet planet2 = new Planet();
        planet2.setName("B");
        Planet planet3 = new Planet();
        planet3.setName("C");

        when(swapiClient.getAllPlanets()).thenReturn(List.of(dto1, dto2, dto3));
        when(planetMapper.toPlanet(dto1)).thenReturn(planet1);
        when(planetMapper.toPlanet(dto2)).thenReturn(planet2);
        when(planetMapper.toPlanet(dto3)).thenReturn(planet3);

        Page<Planet> page = planetsService.getPlanets(1, 2, null, "name", "asc");

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getName()).isEqualTo("C");
        assertThat(page.getTotalElements()).isEqualTo(3);
    }

    @Test
    void getPlanets_excludesUnknownPlanets() {
        SwapiPlanetDTO dto1 = new SwapiPlanetDTO();
        dto1.setName("Tatooine");
        SwapiPlanetDTO dto2 = new SwapiPlanetDTO();
        dto2.setName("unknown");
        dto2.setRotationPeriod("0");
        dto2.setOrbitalPeriod("0");
        dto2.setDiameter("0");
        dto2.setClimate("unknown");
        dto2.setGravity("unknown");
        dto2.setTerrain("unknown");
        dto2.setSurfaceWater(null);
        dto2.setPopulation(null);

        Planet planet1 = new Planet();
        planet1.setName("Tatooine");
        Planet planet2 = new Planet();
        planet2.setName("unknown");
        planet2.setRotationPeriod(0);
        planet2.setOrbitalPeriod(0);
        planet2.setDiameter(0);
        planet2.setClimate("unknown");
        planet2.setGravity("unknown");
        planet2.setTerrain("unknown");
        planet2.setSurfaceWater(null);
        planet2.setPopulation(null);

        when(swapiClient.getAllPlanets()).thenReturn(List.of(dto1, dto2));
        when(planetMapper.toPlanet(dto1)).thenReturn(planet1);
        when(planetMapper.toPlanet(dto2)).thenReturn(planet2);

        Page<Planet> page = planetsService.getPlanets(0, 10, null, null, null);

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getName()).isEqualTo("Tatooine");
        assertThat(page.getTotalElements()).isEqualTo(1);
    }
}
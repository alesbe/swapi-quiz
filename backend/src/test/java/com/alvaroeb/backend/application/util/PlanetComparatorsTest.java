package com.alvaroeb.backend.application.util;

import com.alvaroeb.backend.domain.model.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class PlanetComparatorsTest {

    @Test
    void shouldSortByNameAscending() {
        // Given
        Planet planet1 = new Planet();
        planet1.setName("Earth");
        Planet planet2 = new Planet();
        planet2.setName("Mars");
        Planet planet3 = new Planet();
        planet3.setName("Alderaan");
        Planet planet4 = new Planet();
        planet4.setName(null);

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2, planet3, planet4));

        // When
        planets.sort(PlanetComparators.getComparator("name", Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getName)
                .containsExactly("Alderaan", "Earth", "Mars", null);
    }

    @Test
    void shouldSortByNameDescending() {
        // Given
        Planet planet1 = new Planet();
        planet1.setName("Earth");
        Planet planet2 = new Planet();
        planet2.setName("Mars");
        Planet planet3 = new Planet();
        planet3.setName("Alderaan");
        Planet planet4 = new Planet();
        planet4.setName(null);

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2, planet3, planet4));

        // When
        planets.sort(PlanetComparators.getComparator("name", Sort.Direction.DESC));

        // Then
        assertThat(planets).extracting(Planet::getName)
                .containsExactly(null, "Mars", "Earth", "Alderaan");
    }

    @Test
    void shouldSortByDiameterAscending() {
        // Given
        Planet planet1 = new Planet();
        planet1.setDiameter(12742);
        Planet planet2 = new Planet();
        planet2.setDiameter(6779);
        Planet planet3 = new Planet();
        planet3.setDiameter(null);

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2, planet3));

        // When
        planets.sort(PlanetComparators.getComparator("diameter", Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getDiameter)
                .containsExactly(6779, 12742, null);
    }

    @Test
    void shouldDefaultToNameWhenSortByIsNull() {
        // Given
        Planet planet1 = new Planet();
        planet1.setName("Earth");
        Planet planet2 = new Planet();
        planet2.setName("Mars");

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2));

        // When
        planets.sort(PlanetComparators.getComparator(null, Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getName)
                .containsExactly("Earth", "Mars");
    }

    @Test
    void shouldDefaultToNameWhenSortByIsInvalid() {
        // Given
        Planet planet1 = new Planet();
        planet1.setName("Earth");
        Planet planet2 = new Planet();
        planet2.setName("Mars");

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2));

        // When
        planets.sort(PlanetComparators.getComparator("invalidProperty", Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getName)
                .containsExactly("Earth", "Mars");
    }

    @Test
    void shouldSortByPopulationAscending() {
        // Given
        Planet planet1 = new Planet();
        planet1.setPopulation(7000000000L);
        Planet planet2 = new Planet();
        planet2.setPopulation(8000000L);
        Planet planet3 = new Planet();
        planet3.setPopulation(null);

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2, planet3));

        // When
        planets.sort(PlanetComparators.getComparator("population", Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getPopulation)
                .containsExactly(8000000L, 7000000000L, null);
    }

    @Test
    void shouldSortByCreatedAscending() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        Planet planet1 = new Planet();
        planet1.setCreated(now.minusDays(1));
        Planet planet2 = new Planet();
        planet2.setCreated(now);
        Planet planet3 = new Planet();
        planet3.setCreated(null);

        List<Planet> planets = new ArrayList<>(List.of(planet1, planet2, planet3));

        // When
        planets.sort(PlanetComparators.getComparator("created", Sort.Direction.ASC));

        // Then
        assertThat(planets).extracting(Planet::getCreated)
                .containsExactly(now.minusDays(1), now, null);
    }
}
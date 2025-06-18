package com.alvaroeb.backend.domain.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class PlanetTest {

    @Test
    void testPlanetGettersAndSetters() {
        Planet planet = new Planet();
        String name = "Tatooine";
        Integer rotationPeriod = 23;
        Integer orbitalPeriod = 304;
        Integer diameter = 10465;
        String climate = "arid";
        String gravity = "1 standard";
        String terrain = "desert";
        Integer surfaceWater = 1;
        Long population = 200000L;
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime edited = LocalDateTime.now();

        planet.setName(name);
        planet.setRotationPeriod(rotationPeriod);
        planet.setOrbitalPeriod(orbitalPeriod);
        planet.setDiameter(diameter);
        planet.setClimate(climate);
        planet.setGravity(gravity);
        planet.setTerrain(terrain);
        planet.setSurfaceWater(surfaceWater);
        planet.setPopulation(population);
        planet.setCreated(created);
        planet.setEdited(edited);

        assertThat(planet.getName()).isEqualTo(name);
        assertThat(planet.getRotationPeriod()).isEqualTo(rotationPeriod);
        assertThat(planet.getOrbitalPeriod()).isEqualTo(orbitalPeriod);
        assertThat(planet.getDiameter()).isEqualTo(diameter);
        assertThat(planet.getClimate()).isEqualTo(climate);
        assertThat(planet.getGravity()).isEqualTo(gravity);
        assertThat(planet.getTerrain()).isEqualTo(terrain);
        assertThat(planet.getSurfaceWater()).isEqualTo(surfaceWater);
        assertThat(planet.getPopulation()).isEqualTo(population);
        assertThat(planet.getCreated()).isEqualTo(created);
        assertThat(planet.getEdited()).isEqualTo(edited);
    }

    @Test
    void testPlanetEqualsAndHashCode() {
        Planet planet1 = new Planet();
        planet1.setName("Hoth");
        planet1.setRotationPeriod(23);

        Planet planet2 = new Planet();
        planet2.setName("Hoth");
        planet2.setRotationPeriod(23);

        assertThat(planet1).isEqualTo(planet2);
        assertThat(planet1.hashCode()).isEqualTo(planet2.hashCode());
    }

    @Test
    void testPlanetToString() {
        Planet planet = new Planet();
        planet.setName("Endor");
        String toString = planet.toString();
        assertThat(toString).contains("Endor");
        assertThat(toString).contains("Planet");
    }
}
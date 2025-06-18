package com.alvaroeb.backend.domain.util;

import com.alvaroeb.backend.domain.model.Planet;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PlanetUtilsTest {

    @Test
    void isUnknown_returnsTrue_whenPlanetIsNull() {
        assertThat(PlanetUtils.isUnknown(null)).isTrue();
    }

    @Test
    void isUnknown_returnsTrue_whenAllFieldsMatchUnknown() {
        Planet planet = new Planet();
        planet.setName("unknown");
        planet.setRotationPeriod(0);
        planet.setOrbitalPeriod(0);
        planet.setDiameter(0);
        planet.setClimate("unknown");
        planet.setGravity("unknown");
        planet.setTerrain("unknown");
        planet.setSurfaceWater(null);
        planet.setPopulation(null);

        assertThat(PlanetUtils.isUnknown(planet)).isTrue();
    }

    @Test
    void isUnknown_returnsFalse_whenNameIsNotUnknown() {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setRotationPeriod(0);
        planet.setOrbitalPeriod(0);
        planet.setDiameter(0);
        planet.setClimate("unknown");
        planet.setGravity("unknown");
        planet.setTerrain("unknown");
        planet.setSurfaceWater(null);
        planet.setPopulation(null);

        assertThat(PlanetUtils.isUnknown(planet)).isFalse();
    }

    @Test
    void isUnknown_returnsFalse_whenRotationPeriodIsNotZero() {
        Planet planet = new Planet();
        planet.setName("unknown");
        planet.setRotationPeriod(1);
        planet.setOrbitalPeriod(0);
        planet.setDiameter(0);
        planet.setClimate("unknown");
        planet.setGravity("unknown");
        planet.setTerrain("unknown");
        planet.setSurfaceWater(null);
        planet.setPopulation(null);

        assertThat(PlanetUtils.isUnknown(planet)).isFalse();
    }

    @Test
    void isUnknown_returnsFalse_whenSurfaceWaterIsNotNull() {
        Planet planet = new Planet();
        planet.setName("unknown");
        planet.setRotationPeriod(0);
        planet.setOrbitalPeriod(0);
        planet.setDiameter(0);
        planet.setClimate("unknown");
        planet.setGravity("unknown");
        planet.setTerrain("unknown");
        planet.setSurfaceWater(1);
        planet.setPopulation(null);

        assertThat(PlanetUtils.isUnknown(planet)).isFalse();
    }

    @Test
    void isUnknown_returnsFalse_whenPopulationIsNotNull() {
        Planet planet = new Planet();
        planet.setName("unknown");
        planet.setRotationPeriod(0);
        planet.setOrbitalPeriod(0);
        planet.setDiameter(0);
        planet.setClimate("unknown");
        planet.setGravity("unknown");
        planet.setTerrain("unknown");
        planet.setSurfaceWater(null);
        planet.setPopulation(1000L);

        assertThat(PlanetUtils.isUnknown(planet)).isFalse();
    }
}
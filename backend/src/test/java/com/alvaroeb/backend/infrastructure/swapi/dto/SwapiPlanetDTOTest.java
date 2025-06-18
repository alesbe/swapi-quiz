package com.alvaroeb.backend.infrastructure.swapi.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SwapiPlanetDTOTest {

    @Test
    void testGettersAndSetters() {
        SwapiPlanetDTO dto = new SwapiPlanetDTO();
        dto.setName("Tatooine");
        dto.setRotationPeriod("23");
        dto.setOrbitalPeriod("304");
        dto.setDiameter("10465");
        dto.setClimate("arid");
        dto.setGravity("1 standard");
        dto.setTerrain("desert");
        dto.setSurfaceWater("1");
        dto.setPopulation("200000");
        dto.setCreated("2014-12-09T13:50:49.641000Z");
        dto.setEdited("2014-12-20T20:58:18.411000Z");

        assertThat(dto.getName()).isEqualTo("Tatooine");
        assertThat(dto.getRotationPeriod()).isEqualTo("23");
        assertThat(dto.getOrbitalPeriod()).isEqualTo("304");
        assertThat(dto.getDiameter()).isEqualTo("10465");
        assertThat(dto.getClimate()).isEqualTo("arid");
        assertThat(dto.getGravity()).isEqualTo("1 standard");
        assertThat(dto.getTerrain()).isEqualTo("desert");
        assertThat(dto.getSurfaceWater()).isEqualTo("1");
        assertThat(dto.getPopulation()).isEqualTo("200000");
        assertThat(dto.getCreated()).isEqualTo("2014-12-09T13:50:49.641000Z");
        assertThat(dto.getEdited()).isEqualTo("2014-12-20T20:58:18.411000Z");
    }

    @Test
    void testEqualsAndHashCode() {
        SwapiPlanetDTO dto1 = new SwapiPlanetDTO();
        dto1.setName("Alderaan");
        dto1.setRotationPeriod("24");

        SwapiPlanetDTO dto2 = new SwapiPlanetDTO();
        dto2.setName("Alderaan");
        dto2.setRotationPeriod("24");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testToString() {
        SwapiPlanetDTO dto = new SwapiPlanetDTO();
        dto.setName("Hoth");
        String toString = dto.toString();
        assertThat(toString).contains("Hoth");
    }
}
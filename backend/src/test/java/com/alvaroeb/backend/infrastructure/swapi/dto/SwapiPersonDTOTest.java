package com.alvaroeb.backend.infrastructure.swapi.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;

class SwapiPersonDTOTest {

    @Test
    void testLombokGeneratedMethods() {
        SwapiPersonDTO person = new SwapiPersonDTO();
        person.setName("Luke Skywalker");
        person.setHeight("172");
        person.setMass("77");
        person.setHairColor("blond");
        person.setSkinColor("fair");
        person.setEyeColor("blue");
        person.setBirthYear("19BBY");
        person.setGender("male");
        person.setHomeworld("Tatooine");
        person.setCreated("2014-12-09T13:50:51.644000Z");
        person.setEdited("2014-12-20T21:17:56.891000Z");

        assertThat(person.getName()).isEqualTo("Luke Skywalker");
        assertThat(person.getHeight()).isEqualTo("172");
        assertThat(person.getMass()).isEqualTo("77");
        assertThat(person.getHairColor()).isEqualTo("blond");
        assertThat(person.getSkinColor()).isEqualTo("fair");
        assertThat(person.getEyeColor()).isEqualTo("blue");
        assertThat(person.getBirthYear()).isEqualTo("19BBY");
        assertThat(person.getGender()).isEqualTo("male");
        assertThat(person.getHomeworld()).isEqualTo("Tatooine");
        assertThat(person.getCreated()).isEqualTo("2014-12-09T13:50:51.644000Z");
        assertThat(person.getEdited()).isEqualTo("2014-12-20T21:17:56.891000Z");
    }

    @Test
    void testJsonPropertyMapping() throws Exception {
        String json = "{"
                + "\"name\":\"Leia Organa\","
                + "\"height\":\"150\","
                + "\"mass\":\"49\","
                + "\"hair_color\":\"brown\","
                + "\"skin_color\":\"light\","
                + "\"eye_color\":\"brown\","
                + "\"birth_year\":\"19BBY\","
                + "\"gender\":\"female\","
                + "\"homeworld\":\"Alderaan\","
                + "\"created\":\"2014-12-10T15:20:09.791000Z\","
                + "\"edited\":\"2014-12-20T21:17:50.315000Z\""
                + "}";

        ObjectMapper mapper = new ObjectMapper();
        SwapiPersonDTO person = mapper.readValue(json, SwapiPersonDTO.class);

        assertThat(person.getName()).isEqualTo("Leia Organa");
        assertThat(person.getHeight()).isEqualTo("150");
        assertThat(person.getMass()).isEqualTo("49");
        assertThat(person.getHairColor()).isEqualTo("brown");
        assertThat(person.getSkinColor()).isEqualTo("light");
        assertThat(person.getEyeColor()).isEqualTo("brown");
        assertThat(person.getBirthYear()).isEqualTo("19BBY");
        assertThat(person.getGender()).isEqualTo("female");
        assertThat(person.getHomeworld()).isEqualTo("Alderaan");
        assertThat(person.getCreated()).isEqualTo("2014-12-10T15:20:09.791000Z");
        assertThat(person.getEdited()).isEqualTo("2014-12-20T21:17:50.315000Z");
    }
}
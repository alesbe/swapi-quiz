package com.alvaroeb.backend.infrastructure.swapi.dto;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SwapiPlanetsResponseTest {

    @Test
    void shouldSetAndGetFields() {
        SwapiPlanetsResponse response = new SwapiPlanetsResponse();
        response.setCount(42);
        response.setNext("http://next-page");
        SwapiPlanetDTO planet = new SwapiPlanetDTO();
        response.setResults(List.of(planet));

        assertThat(response.getCount()).isEqualTo(42);
        assertThat(response.getNext()).isEqualTo("http://next-page");
        assertThat(response.getResults()).containsExactly(planet);
    }

    @Test
    void shouldHaveLombokGeneratedEqualsAndHashCode() {
        SwapiPlanetsResponse resp1 = new SwapiPlanetsResponse();
        resp1.setCount(1);
        resp1.setNext("n");
        SwapiPlanetDTO planet = new SwapiPlanetDTO();
        resp1.setResults(List.of(planet));

        SwapiPlanetsResponse resp2 = new SwapiPlanetsResponse();
        resp2.setCount(1);
        resp2.setNext("n");
        resp2.setResults(List.of(planet));

        assertThat(resp1).isEqualTo(resp2);
        assertThat(resp1.hashCode()).isEqualTo(resp2.hashCode());
    }

    @Test
    void shouldHaveLombokGeneratedToString() {
        SwapiPlanetsResponse response = new SwapiPlanetsResponse();
        response.setCount(5);
        response.setNext("next-url");
        response.setResults(null);

        String toString = response.toString();
        assertThat(toString).contains("count=5");
        assertThat(toString).contains("next=next-url");
    }
}
package com.alvaroeb.backend.infrastructure.swapi.dto;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SwapiPeopleResponseTest {

    @Test
    void shouldSetAndGetFields() {
        SwapiPeopleResponse response = new SwapiPeopleResponse();
        response.setCount(42);
        response.setNext("http://next-page");
        SwapiPersonDTO person = new SwapiPersonDTO();
        response.setResults(List.of(person));

        assertThat(response.getCount()).isEqualTo(42);
        assertThat(response.getNext()).isEqualTo("http://next-page");
        assertThat(response.getResults()).containsExactly(person);
    }

    @Test
    void shouldHaveEqualsAndHashCode() {
        SwapiPersonDTO person = new SwapiPersonDTO();
        SwapiPeopleResponse r1 = new SwapiPeopleResponse();
        r1.setCount(1);
        r1.setNext("n");
        r1.setResults(List.of(person));

        SwapiPeopleResponse r2 = new SwapiPeopleResponse();
        r2.setCount(1);
        r2.setNext("n");
        r2.setResults(List.of(person));

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    void shouldHaveToString() {
        SwapiPeopleResponse response = new SwapiPeopleResponse();
        response.setCount(5);
        response.setNext("next-url");
        response.setResults(List.of());

        String str = response.toString();
        assertThat(str).contains("count=5");
        assertThat(str).contains("next=next-url");
    }
}
package com.alvaroeb.backend.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    void testPersonGettersAndSetters() {
        Person person = new Person();
        person.setName("Luke Skywalker");
        person.setHeight(172);
        person.setMass(77);
        person.setHairColor("Blond");
        person.setSkinColor("Fair");
        person.setEyeColor("Blue");
        person.setBirthYear("19BBY");
        person.setGender("Male");

        Planet planet = new Planet();
        person.setHomeworld(planet);

        LocalDateTime now = LocalDateTime.now();
        person.setCreated(now);
        person.setEdited(now);

        assertThat(person.getName()).isEqualTo("Luke Skywalker");
        assertThat(person.getHeight()).isEqualTo(172);
        assertThat(person.getMass()).isEqualTo(77);
        assertThat(person.getHairColor()).isEqualTo("Blond");
        assertThat(person.getSkinColor()).isEqualTo("Fair");
        assertThat(person.getEyeColor()).isEqualTo("Blue");
        assertThat(person.getBirthYear()).isEqualTo("19BBY");
        assertThat(person.getGender()).isEqualTo("Male");
        assertThat(person.getHomeworld()).isSameAs(planet);
        assertThat(person.getCreated()).isEqualTo(now);
        assertThat(person.getEdited()).isEqualTo(now);
    }

    @Test
    void testPersonEqualsAndHashCode() {
        Person person1 = new Person();
        person1.setName("Leia Organa");

        Person person2 = new Person();
        person2.setName("Leia Organa");

        assertThat(person1).isEqualTo(person2);
        assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
    }

    @Test
    void testPersonToString() {
        Person person = new Person();
        person.setName("Han Solo");
        String toString = person.toString();
        assertThat(toString).contains("Han Solo");
    }
}